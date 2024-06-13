package com.example.profilefeatures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.entity.ProductEntity
import com.nabiha.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val preferenceDatastore: PreferenceDatastore,
): ViewModel(){

    private var _profileState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val profileState get() = _profileState.asStateFlow()

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val getProfile = preferenceDatastore.getProfile()
            if (getProfile != null){
                _profileState.value = ProfileState.Success(getProfile)
            }else{
                _profileState.value = ProfileState.Error("Failed Fetch Profile!")
            }

        }
    }

}

sealed interface ProfileState {
    object Loading : ProfileState
    data class Success(val data: UserEntity) : ProfileState
    data class Error(val message: String) : ProfileState
}