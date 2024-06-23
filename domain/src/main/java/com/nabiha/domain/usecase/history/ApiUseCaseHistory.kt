package com.nabiha.domain.usecase.history

import com.nabiha.apiresponse.histories.HistoriesApiRequest
import com.nabiha.apiresponse.histories.HistoryApiRequest
import com.nabiha.domain.utils.Result
import com.nabiha.domain.utils.UseCase
import com.nabiha.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseHistory : UseCase {
    suspend fun fetchAllHistory(): Flow<Result<List<HistoryEntity>>>
    suspend fun fetchHistory(id: Long): Flow<Result<HistoryEntity>>
    suspend fun createHistories(request: HistoriesApiRequest): Flow<Result<List<HistoryEntity>>>
}