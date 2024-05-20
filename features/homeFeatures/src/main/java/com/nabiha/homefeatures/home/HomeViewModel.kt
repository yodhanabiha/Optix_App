package com.nabiha.homefeatures.home

import android.app.appsearch.AppSearchSchema.StringPropertyConfig
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.ProductUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.ProductEntity
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
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private var _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    private var _username = MutableStateFlow<String>("")
    val homeUiState get() = _homeUiState.asStateFlow()
    val username get() = _username.asStateFlow()

    init {
        fetchName()
        fetchContent()
    }

    suspend fun checkToken(): Boolean {
        val token = preferenceDatastore.getToken().first()
        return token.isNotEmpty()
    }

    private fun fetchName(){
        viewModelScope.launch {
             _username.value = preferenceDatastore.getProfile()?.name.toString()
        }
    }

    private fun fetchContent() {
        viewModelScope.launch {
            val token = preferenceDatastore.getToken().first()
            Timber.d("isi token: $token")
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