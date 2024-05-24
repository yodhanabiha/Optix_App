package com.nabiha.data.repoiml

import com.nabiha.data.apiservice.ApiService
import com.nabiha.data.datastore.PreferenceDatastore
import com.nabiha.data.mapper.product.ProductMapper
import com.nabiha.data.mapper.product.ProductsMapper
import com.nabiha.data.utils.NetworkBoundResource
import com.nabiha.data.utils.mapFromApiResponse
import com.nabiha.domain.repository.ProductRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferenceDatastore: PreferenceDatastore,
    private val networkBoundResources: NetworkBoundResource,
    private val productsMapper: ProductsMapper,
    private val productMapper: ProductMapper
):ProductRepository {
    override suspend fun fetchAllProducts(): Flow<Result<List<ProductEntity>>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchProducts(
                    headers = mapOf("Authorization" to "Bearer $token")
                )
            }, productsMapper
        )
    }

    override suspend fun fetchProduct(id: Long): Flow<Result<ProductEntity>> {
        val token = preferenceDatastore.getToken().first()
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchProduct(
                    headers = mapOf("Authorization" to "Bearer $token"),
                    id = id
                )
            }, productMapper
        )
    }
}