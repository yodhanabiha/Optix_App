package com.nabiha.data.repoiml

import com.nabiha.apiresponse.histories.HistoriesApiRequest
import com.nabiha.apiresponse.histories.HistoryApiRequest
import com.nabiha.data.apiservice.ApiService
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.data.mapper.history.HistoriesMapper
import com.nabiha.data.mapper.history.HistoryMapper
import com.nabiha.data.utils.NetworkBoundResource
import com.nabiha.data.utils.mapFromApiResponse
import com.nabiha.domain.repository.HistoryRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HistoryRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferenceDatastore: PreferenceDatastore,
    private val networkBoundResources: NetworkBoundResource,
    private val historyMapper: HistoryMapper,
    private val historiesMapper: HistoriesMapper
) : HistoryRepository {
    override suspend fun fetchAllHistory(): Flow<Result<List<HistoryEntity>>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchHistories(
                    headers = mapOf("Authorization" to "Bearer $token")
                )
            }, historiesMapper
        )
    }

    override suspend fun fetchHistory(id: Long): Flow<Result<HistoryEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchHistory(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = id
                )
            }, historyMapper
        )
    }

    override suspend fun createHistories(request: HistoriesApiRequest): Flow<Result<List<HistoryEntity>>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.createHistories(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    request = request
                )
            }, historiesMapper
        )
    }

}