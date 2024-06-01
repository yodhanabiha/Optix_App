package com.nabiha.domain.usecase.wishlist

import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.domain.repository.WishListRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.LikeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishListUseCase @Inject constructor(
    private val repository: WishListRepository
):ApiUseCaseWishList {
    override suspend fun wishList(): Flow<Result<List<LikeEntity>>> {
        return repository.fetchWhistList()
    }

    override suspend fun likeProduct(request: LikeApiRequest): Flow<Result<LikeEntity>> {
        return repository.likeProduct(request)
    }

    override suspend fun unLikeProduct(id: Long): Flow<Result<String>> {
        return repository.unLikeProduct(id)
    }


}