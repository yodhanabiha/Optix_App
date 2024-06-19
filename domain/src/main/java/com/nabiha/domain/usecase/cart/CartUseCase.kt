package com.nabiha.domain.usecase.cart

import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.domain.repository.CartRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.CartEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCase @Inject constructor(
    private val repository: CartRepository
) : ApiUseCaseCarts{
    override suspend fun fetchAllCarts(): Flow<Result<List<CartEntity>>> {
        return repository.fetchAllCarts()
    }

    override suspend fun fetchCart(id: Long): Flow<Result<CartEntity>> {
        return repository.fetchCart(id)
    }

    override suspend fun updateCart(id: Long, request: CartApiRequest): Flow<Result<CartEntity>> {
        return repository.updateCart(id, request)
    }

    override suspend fun createCart(request: CartApiRequest): Flow<Result<CartEntity>> {
        return repository.createCart(request)
    }

    override suspend fun deleteCart(id: Long): Flow<Result<String>> {
        return repository.deleteCart(id)
    }
}