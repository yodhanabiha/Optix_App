package com.example.cartfeatures.cartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.cart.CartUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.CartEntity
import com.nabiha.entity.LikeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {
    private var _cartUiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val cartUiState get() = _cartUiState.asStateFlow()

    init {
        fetchCarts()
    }

    private fun fetchCarts() {
        viewModelScope.launch {
            cartUseCase.fetchAllCarts().collect { response ->
                when (response) {
                    is Result.Error -> _cartUiState.value = CartUiState.Error(response.errorMessage)
                    is Result.Loading -> _cartUiState.value = CartUiState.Loading
                    is Result.Success -> _cartUiState.value = CartUiState.Success(response.data)
                }

            }
        }
    }

    fun updateCarts(id: Long, request: CartApiRequest) {
        viewModelScope.launch {
            cartUseCase.updateCart(id, request).collect { response ->
                when (response) {
                    is Result.Error -> {
                        Timber.e(response.errorMessage)
                    }
                    is Result.Loading -> {}
                    is Result.Success -> Timber.d("Update Success")
                }
            }
        }
    }

}

sealed interface CartUiState {
    object Loading : CartUiState
    data class Success(val data: List<CartEntity>) : CartUiState
    data class Error(val message: String) : CartUiState
}