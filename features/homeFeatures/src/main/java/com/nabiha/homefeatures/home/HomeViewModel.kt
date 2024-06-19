package com.nabiha.homefeatures.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.product.ProductUseCase
import com.nabiha.domain.usecase.wishlist.WishListUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.ProductEntity
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceDatastore: PreferenceDatastore,
    private val productUseCase: ProductUseCase,
    private val wishListUseCase: WishListUseCase
) : ViewModel() {

    private var _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    private var _user = MutableStateFlow<UserEntity>(UserEntity())
    val homeUiState get() = _homeUiState.asStateFlow()
    val user get() = _user.asStateFlow()

    init {
        fetchName()
        fetchContent()
    }

    suspend fun checkToken(): Boolean {
        val token = preferenceDatastore.getToken().first()
        return token.isNotEmpty()
    }

    private fun fetchName() {
        viewModelScope.launch {
            _user.value = preferenceDatastore.getProfile() ?: UserEntity()
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

    private fun fetchContent() {
        viewModelScope.launch {
            productUseCase.products().collect { response ->
                when (response) {
                    is Result.Error -> _homeUiState.value = HomeUiState.Error(response.errorMessage)
                    is Result.Loading -> _homeUiState.value = HomeUiState.Loading
                    is Result.Success -> _homeUiState.value = HomeUiState.Success(response.data)
                }
            }
        }
    }

}

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val data: List<ProductEntity>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}