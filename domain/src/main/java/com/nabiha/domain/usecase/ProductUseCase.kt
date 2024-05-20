package com.nabiha.domain.usecase

import com.nabiha.domain.repository.ProductRepository
import com.nabiha.domain.utils.ApiUseCaseProduct
import com.nabiha.domain.utils.Result
import com.nabiha.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ProductRepository
): ApiUseCaseProduct<ProductEntity> {
    override suspend fun products(): Flow<Result<List<ProductEntity>>> {
        return repository.fetchAllProducts()
    }

}