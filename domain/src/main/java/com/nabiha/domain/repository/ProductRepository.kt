package com.nabiha.domain.repository

import com.nabiha.domain.utils.Result
import com.nabiha.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchAllProducts(): Flow<Result<List<ProductEntity>>>

    suspend fun fetchProduct(id: Long): Flow<Result<ProductEntity>>
}