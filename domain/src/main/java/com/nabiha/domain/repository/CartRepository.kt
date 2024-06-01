package com.nabiha.domain.repository

import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.domain.utils.Result
import com.nabiha.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun fetchAllCarts(): Flow<Result<List<CartEntity>>>
    suspend fun fetchCart(id: Long): Flow<Result<CartEntity>>
    suspend fun createCart(request: CartApiRequest): Flow<Result<CartEntity>>
    suspend fun deleteCart(id: Long): Flow<Result<String>>
}