package com.example.cartfeatures.cartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.apiresponse.histories.HistoriesApiRequest
import com.nabiha.apiresponse.histories.HistoryApiRequest
import com.nabiha.domain.usecase.cart.CartUseCase
import com.nabiha.domain.usecase.history.HistoryUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.CartEntity
import com.nabiha.entity.HistoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val historyUseCase: HistoryUseCase
) : ViewModel() {
    private var _cartUiState = MutableStateFlow<CartUiState>(CartUiState.Loading)
    val cartUiState get() = _cartUiState.asStateFlow()

    private var _historyCreated = MutableStateFlow<HistoryCreatedState>(HistoryCreatedState.Loading)
    val historyCreated get() = _historyCreated.asStateFlow()

    init {
        viewModelScope.launch {
            fetchCarts()
        }
    }

    fun createHistories(data: HistoriesApiRequest){
        viewModelScope.launch {
            historyUseCase.createHistories(data).collect {response->
                when(response){
                    is Result.Error -> _historyCreated.value = HistoryCreatedState.Error(response.errorMessage)
                    is Result.Loading -> _historyCreated.value = HistoryCreatedState.Loading
                    is Result.Success -> _historyCreated.value = HistoryCreatedState.Success(response.data)
                }
            }
        }
    }


    fun deleteCarts(id: Long){
        viewModelScope.launch {
            cartUseCase.deleteCart(id).collect{ response->
                when(response){
                    is Result.Error -> Timber.e(response.errorMessage)
                    is Result.Loading -> {}
                    is Result.Success -> Timber.e(response.data)
                }
            }
        }
    }

    fun deleteAllCarts(){
        viewModelScope.launch {
            cartUseCase.deleteAllCart().collect{ response->
                when(response){
                    is Result.Error -> Timber.e(response.errorMessage)
                    is Result.Loading -> {}
                    is Result.Success -> Timber.e(response.data)
                }
            }
        }
    }

    fun refreshCartList() {
        fetchCarts()
        _historyCreated.value = HistoryCreatedState.Loading
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

sealed interface HistoryCreatedState {
    object Loading : HistoryCreatedState
    data class Success(val data: List<HistoryEntity>) : HistoryCreatedState
    data class Error(val message: String) : HistoryCreatedState
}