package com.example.profilefeatures.edit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.domain.usecase.user.UsersUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val datastore: PreferenceDatastore
): ViewModel() {

    private var _eprofileState = MutableStateFlow<EProfileState>(EProfileState.Idle)
    val eprofileState get() = _eprofileState.asStateFlow()


    fun updateProfile(id: Long, data: UserApiUpdateRequest, context: Context){
        viewModelScope.launch {
           usersUseCase.update(id,context, data).collect{result->
               when(result){
                   is Result.Error -> _eprofileState.value = EProfileState.Error(result.errorMessage)
                   is Result.Loading -> _eprofileState.value = EProfileState.Loading
                   is Result.Success -> {
                       _eprofileState.value = EProfileState.Success(result.data)
                       datastore.setProfile(result.data)
                   }
               }
           }
        }
    }
    fun updateProfile(data: UserApiUpdateRequest){
        viewModelScope.launch {
            usersUseCase.fetchUpdateUser(data).collect{result->
                when(result){
                    is Result.Error -> _eprofileState.value = EProfileState.Error(result.errorMessage)
                    is Result.Loading -> _eprofileState.value = EProfileState.Loading
                    is Result.Success -> {
                        _eprofileState.value = EProfileState.Success(result.data)
                        datastore.setProfile(result.data)
                    }
                }
            }
        }
    }


    fun resetEProfileState() {
        _eprofileState.value = EProfileState.Idle
    }

}

sealed interface EProfileState {
    object Loading : EProfileState
    object Idle : EProfileState
    data class Success(val data: UserEntity) : EProfileState
    data class Error(val message: String) : EProfileState
}