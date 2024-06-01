package com.nabiha.domain.repository

import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.domain.utils.Result
import com.nabiha.entity.LikeEntity
import kotlinx.coroutines.flow.Flow

interface WishListRepository {
    suspend fun fetchWhistList(): Flow<Result<List<LikeEntity>>>
    suspend fun likeProduct(request: LikeApiRequest): Flow<Result<LikeEntity>>
    suspend fun unLikeProduct(id: Long): Flow<Result<String>>
}