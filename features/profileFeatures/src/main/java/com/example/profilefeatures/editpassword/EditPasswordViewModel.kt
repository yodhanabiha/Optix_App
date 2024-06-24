package com.example.profilefeatures.editpassword

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilefeatures.edit.EProfileState
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.user.UsersUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPasswordViewModel @Inject constructor(
    private val datastore: PreferenceDatastore,
    private val usersUseCase: UsersUseCase,
) : ViewModel() {

    private var _epPasswordState = MutableStateFlow<EPasswordState>(EPasswordState.Idle)
    val ePasswordState get() = _epPasswordState.asStateFlow()


    fun updatePassword(newPassword: String){
        viewModelScope.launch {
            usersUseCase.fetchUpdatePassword(newPassword).collect{result->
                when(result){
                    is Result.Error -> _epPasswordState.value = EPasswordState.Error(result.errorMessage)
                    is Result.Loading -> _epPasswordState.value = EPasswordState.Loading
                    is Result.Success -> {
                        _epPasswordState.value = EPasswordState.Success(result.data)
                        datastore.setProfile(result.data)
                    }
                }
            }
        }
    }

    fun resetEPasswordState() {
        _epPasswordState.value = EPasswordState.Idle
    }

}

sealed interface EPasswordState {
    object Loading : EPasswordState
    object Idle : EPasswordState
    data class Success(val data: UserEntity) : EPasswordState
    data class Error(val message: String) : EPasswordState
}