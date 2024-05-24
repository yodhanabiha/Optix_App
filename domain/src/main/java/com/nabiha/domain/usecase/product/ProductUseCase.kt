package com.nabiha.domain.usecase.product

import com.nabiha.domain.repository.ProductRepository
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

    override suspend fun product(id: Long): Flow<Result<ProductEntity>> {
        return repository.fetchProduct(id)
    }

}