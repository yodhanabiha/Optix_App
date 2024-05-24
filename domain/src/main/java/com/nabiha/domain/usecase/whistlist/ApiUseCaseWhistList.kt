package com.nabiha.domain.usecase.whistlist

import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.domain.utils.Result
import com.nabiha.domain.utils.UseCase
import com.nabiha.entity.LikeEntity
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseWhistList : UseCase {
    suspend fun whistList(): Flow<Result<List<LikeEntity>>>
    suspend fun likeProduct(request: LikeApiRequest): Flow<Result<LikeEntity>>
    suspend fun unLikeProduct(id: Long): Flow<Result<String>>
}