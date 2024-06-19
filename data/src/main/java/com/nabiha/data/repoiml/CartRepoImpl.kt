package com.nabiha.data.repoiml

import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.data.apiservice.ApiService
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.data.mapper.cart.CartDelMapper
import com.nabiha.data.mapper.cart.CartMapper
import com.nabiha.data.mapper.cart.CartsMapper
import com.nabiha.data.utils.NetworkBoundResource
import com.nabiha.data.utils.mapFromApiResponse
import com.nabiha.domain.repository.CartRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.CartEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferenceDatastore: PreferenceDatastore,
    private val networkBoundResources: NetworkBoundResource,
    private val cartMapper: CartMapper,
    private val cartsMapper: CartsMapper,
    private val cartDelMapper: CartDelMapper
) : CartRepository {
    override suspend fun fetchAllCarts(): Flow<Result<List<CartEntity>>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchCarts(
                    headers = mapOf("Authorization" to "Bearer $token")
                )
            }, cartsMapper
        )
    }

    override suspend fun fetchCart(id: Long): Flow<Result<CartEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchCart(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = id
                )
            }, cartMapper
        )
    }

    override suspend fun updateCart(id: Long, request: CartApiRequest): Flow<Result<CartEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.updateCart(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = id,
                    request = request
                )
            }, cartMapper
        )
    }

    override suspend fun createCart(request: CartApiRequest): Flow<Result<CartEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.createCarts(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    request = request
                )
            }, cartMapper
        )
    }

    override suspend fun deleteCart(id: Long): Flow<Result<String>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.deleteCart(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = id
                )

            }, cartDelMapper
        )
    }
}