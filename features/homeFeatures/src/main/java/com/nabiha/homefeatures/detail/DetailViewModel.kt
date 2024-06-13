package com.nabiha.homefeatures.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.cart.CartUseCase
import com.nabiha.domain.usecase.product.ProductUseCase
import com.nabiha.domain.usecase.wishlist.WishListUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.ProductEntity
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val preferenceDatastore: PreferenceDatastore,
    private val productUseCase: ProductUseCase,
    private val wishListUseCase: WishListUseCase,
    private val cartUseCase: CartUseCase
) : ViewModel() {

    private var _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    private var _user = MutableStateFlow<UserEntity>(UserEntity())
    private var _productsRecommended = MutableStateFlow<RecommendedState>(RecommendedState.Loading)
    val detailUiState get() = _detailUiState.asStateFlow()
    val recommendedState get() = _productsRecommended.asStateFlow()
    val user get() = _user.asStateFlow()

    init {
        fetchUser()
        fetchRecommend()
    }

    private fun fetchRecommend() {
        viewModelScope.launch {
            productUseCase.products().collect { response ->
                when (response) {
                    is Result.Error -> _productsRecommended.value =
                        RecommendedState.Error(response.errorMessage)

                    is Result.Loading -> _productsRecommended.value = RecommendedState.Loading
                    is Result.Success -> _productsRecommended.value =
                        RecommendedState.Success(response.data.shuffled().take(6))
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _user.value = preferenceDatastore.getProfile() ?: UserEntity()
        }
    }

    fun fetchDetailProduct(id: Long) {
        viewModelScope.launch {
            productUseCase.product(id).collect { response ->
                when (response) {
                    is Result.Error -> _detailUiState.value =
                        DetailUiState.Error(response.errorMessage)

                    is Result.Loading -> _detailUiState.value = DetailUiState.Loading
                    is Result.Success -> {
                        val result = response.data
                        _detailUiState.value = DetailUiState.Success(result)
                    }
                }

            }
        }
    }

    fun addCart(request: CartApiRequest) {
        viewModelScope.launch {
            cartUseCase.createCart(request).collect {}
        }
    }

    fun likeProduct(productId: Long, onResult: (Long?) -> Unit) {
        val request = LikeApiRequest(productId, user.value.id)
        viewModelScope.launch {
            wishListUseCase.likeProduct(request).collect { response ->
                when (response) {
                    is Result.Error -> {
                        Timber.e(response.errorMessage)
                        onResult(null)
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

sealed interface RecommendedState {
    object Loading : RecommendedState
    data class Success(val data: List<ProductEntity>) : RecommendedState
    data class Error(val message: String) : RecommendedState
}

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val data: ProductEntity) : DetailUiState
    data class Error(val message: String) : DetailUiState
}