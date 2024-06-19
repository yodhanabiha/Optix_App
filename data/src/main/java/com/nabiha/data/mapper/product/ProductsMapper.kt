package com.nabiha.data.mapper.product

import com.nabiha.apiresponse.products.ProductsApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.ProductEntity
import timber.log.Timber
import javax.inject.Inject

class ProductsMapper @Inject constructor() : Mapper<ProductsApiResponse, List<ProductEntity>> {
    override fun mapFromApiResponse(type: ProductsApiResponse): List<ProductEntity> {
        return if (type.status == "SUCCESS") {
            type.results
        } else {
            emptyList()
        }
    }
}