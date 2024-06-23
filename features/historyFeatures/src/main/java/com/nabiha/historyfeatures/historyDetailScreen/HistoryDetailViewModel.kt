package com.nabiha.historyfeatures.historyDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabiha.domain.usecase.history.HistoryUseCase
import com.nabiha.domain.utils.Result
import com.nabiha.entity.HistoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryDetailViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase
) : ViewModel(){

    private var _historyUiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Loading)
    val historyUiState get() = _historyUiState.asStateFlow()

    fun fetchHistoryDetail(id: Long) {
        viewModelScope.launch {
            historyUseCase.fetchHistory(id).collect { response ->
                when (response) {
                    is Result.Error -> _historyUiState.value =
                        HistoryUiState.Error(response.errorMessage)

                    is Result.Loading -> _historyUiState.value = HistoryUiState.Loading
                    is Result.Success -> {
                        _historyUiState.value = HistoryUiState.Success(response.data)
                    }
                }

            }
        }
    }
}

sealed interface HistoryUiState {
    object Loading : HistoryUiState
    data class Success(val data: HistoryEntity) : HistoryUiState
    data class Error(val message: String) : HistoryUiState
}