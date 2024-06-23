package com.nabiha.domain.usecase.history

import com.nabiha.apiresponse.histories.HistoriesApiRequest
import com.nabiha.apiresponse.histories.HistoryApiRequest
import com.nabiha.domain.repository.HistoryRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) : ApiUseCaseHistory {
    override suspend fun fetchAllHistory(): Flow<Result<List<HistoryEntity>>> {
        return historyRepository.fetchAllHistory()
    }

    override suspend fun fetchHistory(id: Long): Flow<Result<HistoryEntity>> {
        return historyRepository.fetchHistory(id)
    }

    override suspend fun createHistories(request: HistoriesApiRequest): Flow<Result<List<HistoryEntity>>> {
        return historyRepository.createHistories(request)
    }
}