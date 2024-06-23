package com.nabiha.domain.repository

import com.nabiha.apiresponse.histories.HistoriesApiRequest
import com.nabiha.apiresponse.histories.HistoryApiRequest
import com.nabiha.domain.utils.Result
import com.nabiha.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun fetchAllHistory(): Flow<Result<List<HistoryEntity>>>
    suspend fun fetchHistory(id: Long): Flow<Result<HistoryEntity>>
    suspend fun createHistories(request: HistoriesApiRequest): Flow<Result<List<HistoryEntity>>>
}