package com.nabiha.authfeatures.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.domain.usecase.UsersUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUseCase: UsersUseCase
): ViewModel(){

    private val _registerUiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Loading)
    val registerUiState get() = _registerUiState.asStateFlow()

    fun fetchRegister(userReg: UserApiRegisterRequest){
        viewModelScope.launch {
            userUseCase.register(userReg).collect{response->
                when(response){
                    is Result.Error -> _registerUiState.value =
                        RegisterUiState.Error(response.message)
                    is Result.Loading -> _registerUiState.value = RegisterUiState.Loading
                    is Result.Success -> _registerUiState.value =
                        RegisterUiState.Success(response.data)
                }

            }
        }
    }


}

sealed interface RegisterUiState{
    object Loading : RegisterUiState
    data class Success(val data: UserEntity): RegisterUiState
    data class Error(val message:String) : RegisterUiState
}

