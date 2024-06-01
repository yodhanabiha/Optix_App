package com.nabiha.data.repoiml

import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.data.apiservice.ApiService
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.data.mapper.whistlist.LikeMapper
import com.nabiha.data.mapper.whistlist.UnLikeMapper
import com.nabiha.data.mapper.whistlist.WhistListMapper
import com.nabiha.data.utils.NetworkBoundResource
import com.nabiha.data.utils.mapFromApiResponse
import com.nabiha.domain.repository.WishListRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.LikeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class WishListRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferenceDatastore: PreferenceDatastore,
    private val networkBoundResources: NetworkBoundResource,
    private val likeMapper: LikeMapper,
    private val unLikeMapper: UnLikeMapper,
    private val whistList: WhistListMapper
):WishListRepository{
    override suspend fun fetchWhistList(): Flow<Result<List<LikeEntity>>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchWhistLists(
                    headers = mapOf("Authorization" to "Bearer $token")
                )
            }, whistList
        )
    }

    override suspend fun likeProduct(request: LikeApiRequest): Flow<Result<LikeEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.likeProduct(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    request = request
                )
            },likeMapper
        )
    }

    override suspend fun unLikeProduct(id: Long): Flow<Result<String>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.unlikeProduct(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = id
                )
            }, unLikeMapper
        )
    }

}