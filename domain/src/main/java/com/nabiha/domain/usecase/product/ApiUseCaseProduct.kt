package com.nabiha.domain.usecase.product

import com.nabiha.domain.utils.Result
import com.nabiha.domain.utils.UseCase
import com.nabiha.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseProduct<Entity> : UseCase {
    suspend fun products(): Flow<Result<List<Entity>>>
    suspend fun product(id: Long): Flow<Result<ProductEntity>>
}