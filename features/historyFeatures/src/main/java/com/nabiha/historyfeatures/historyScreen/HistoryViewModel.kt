package com.nabiha.historyfeatures.historyScreen

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
class HistoryViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase
) : ViewModel(){

    private var _historyListUiState = MutableStateFlow<HistoryListUiState>(HistoryListUiState.Loading)
    val historyListUiState get() = _historyListUiState.asStateFlow()

    init {
        fetchHistory()
    }

    private fun fetchHistory() {
        viewModelScope.launch {
            historyUseCase.fetchAllHistory().collect { response ->
                when (response) {
                    is Result.Error -> _historyListUiState.value =
                        HistoryListUiState.Error(response.errorMessage)

                    is Result.Loading -> _historyListUiState.value = HistoryListUiState.Loading
                    is Result.Success -> {
                        _historyListUiState.value = HistoryListUiState.Success(response.data)
                    }
                }

            }
        }
    }

}

sealed interface HistoryListUiState {
    object Loading : HistoryListUiState
    data class Success(val data: List<HistoryEntity>) : HistoryListUiState
    data class Error(val message: String) : HistoryListUiState
}