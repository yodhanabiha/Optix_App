package com.nabiha.data.mapper.product

import com.nabiha.apiresponse.products.ProductsApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.ProductEntity
import javax.inject.Inject

class ProductsMapper @Inject constructor() : Mapper<ProductsApiResponse, List<ProductEntity>> {
    override fun mapFromApiResponse(type: ProductsApiResponse): List<ProductEntity> {
        return if (type.success == "SUCCESS") {
            type.result
        } else {
            emptyList()
        }
    }
}