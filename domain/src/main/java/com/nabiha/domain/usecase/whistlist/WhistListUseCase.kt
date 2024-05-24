package com.nabiha.domain.usecase.whistlist

import com.nabiha.apiresponse.likes.LikeApiRequest
import com.nabiha.domain.repository.ProductRepository
import com.nabiha.domain.repository.WhistListRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.LikeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WhistListUseCase @Inject constructor(
    private val repository: WhistListRepository
):ApiUseCaseWhistList {
    override suspend fun whistList(): Flow<Result<List<LikeEntity>>> {
        return repository.fetchWhistList()
    }

    override suspend fun likeProduct(request: LikeApiRequest): Flow<Result<LikeEntity>> {
        return repository.likeProduct(request)
    }

    override suspend fun unLikeProduct(id: Long): Flow<Result<String>> {
        return repository.unLikeProduct(id)
    }


}