package com.nabiha.data.mapper.product

import com.nabiha.apiresponse.products.ProductApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.ProductEntity
import javax.inject.Inject

class ProductMapper @Inject constructor() : Mapper<ProductApiResponse, ProductEntity> {
    override fun mapFromApiResponse(type: ProductApiResponse): ProductEntity {
        return if (type.success == "SUCCESS") {
            val res = type.result
            ProductEntity(
                res.id, res.title, res.description, res.imageurl, res.price
            )
        } else {
            ProductEntity()
        }
    }
}