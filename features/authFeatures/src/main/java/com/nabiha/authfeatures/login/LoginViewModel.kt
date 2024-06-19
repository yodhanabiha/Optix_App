package com.nabiha.authfeatures.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.user.UsersUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntityLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val preferenceDatastore: PreferenceDatastore
): ViewModel(){

    private var _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Neutral)
    val loginUiState get() = _loginUiState.asStateFlow()

    fun fetchRegister(loginReq: UserApiLoginRequest){
        viewModelScope.launch {
            usersUseCase.login(loginReq).collect{response->
                when(response){
                    is Result.Error -> _loginUiState.value =
                        LoginUiState.Error(response.errorMessage)
                    is Result.Loading -> _loginUiState.value = LoginUiState.Loading
                    is Result.Success -> {
                        val result = response.data
                        _loginUiState.value = LoginUiState.Success(result)
                        preferenceDatastore.setToken(result.token)
                        preferenceDatastore.setProfile(result.user)
                    }
                }

            }
        }
    }

    fun resetRegisterUiState() {
        _loginUiState.value = LoginUiState.Neutral
    }

}

sealed interface LoginUiState{
    object Loading : LoginUiState
    object Neutral : LoginUiState
    data class Success(val data: UserEntityLogin): LoginUiState
    data class Error(val message:String) : LoginUiState
}