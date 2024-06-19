package com.nabiha.domain.usecase.wishlist

import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.domain.utils.Result
import com.nabiha.domain.utils.UseCase
import com.nabiha.entity.LikeEntity
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseWishList : UseCase {
    suspend fun wishList(): Flow<Result<List<LikeEntity>>>
    suspend fun likeProduct(request: LikeApiRequest): Flow<Result<LikeEntity>>
    suspend fun unLikeProduct(id: Long): Flow<Result<String>>
}