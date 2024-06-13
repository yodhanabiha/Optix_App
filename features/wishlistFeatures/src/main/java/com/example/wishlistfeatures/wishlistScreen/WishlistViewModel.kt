package com.example.wishlistfeatures.wishlistScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.cart.CartUseCase
import com.nabiha.domain.usecase.wishlist.WishListUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.LikeEntity
import com.nabiha.entity.ProductEntity
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val preferenceDatastore: PreferenceDatastore,
    private val wishListUseCase: WishListUseCase,
    private val cartUseCase: CartUseCase
) : ViewModel() {

    private var _wishListUiState = MutableStateFlow<WishListUiState>(WishListUiState.Loading)
    val wishListUiState get() = _wishListUiState.asStateFlow()

    init {
        fetchWishList()
    }

    fun addCart(request: CartApiRequest) {
        viewModelScope.launch {
            cartUseCase.createCart(request).collect {}
        }
    }

    private fun fetchWishList() {
        viewModelScope.launch {
            wishListUseCase.wishList().collect { response ->
                when (response) {
                    is Result.Error -> _wishListUiState.value =
                        WishListUiState.Error(response.errorMessage)

                    is Result.Loading -> _wishListUiState.value = WishListUiState.Loading
                    is Result.Success -> {
                        _wishListUiState.value = WishListUiState.Success(response.data)
                    }
                }

            }
        }
    }

    fun likeProduct(productId: Long, onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val user = preferenceDatastore.getProfile() ?: UserEntity()
            val request = LikeApiRequest(productId, user.id)
            wishListUseCase.likeProduct(request).collect { response ->
                when (response) {
                    is Result.Error -> {
                        Timber.e(response.errorMessage)
                        onResult(0)
                    }
                    is Result.Loading -> {}
                    is Result.Success -> {
                        onResult(response.data.id)
                    }
                }
            }
        }
    }

    fun unLikeProduct(likeId: Long) {
        viewModelScope.launch {
            wishListUseCase.unLikeProduct(likeId).collect {}
        }
    }


}

sealed interface WishListUiState {
    object Loading : WishListUiState
    data class Success(val data: List<LikeEntity>) : WishListUiState
    data class Error(val message: String) : WishListUiState
}