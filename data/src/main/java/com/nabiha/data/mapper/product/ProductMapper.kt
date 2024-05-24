package com.nabiha.data.mapper.product

import com.nabiha.apiresponse.products.ProductApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.ProductEntity
import timber.log.Timber
import javax.inject.Inject

class ProductMapper @Inject constructor() : Mapper<ProductApiResponse, ProductEntity> {
    override fun mapFromApiResponse(type: ProductApiResponse): ProductEntity {
        return if (type.status == "SUCCESS") {
            val res = type.results
            ProductEntity(
                res.id, res.title, res.description, res.spec,res.imageurl, res.price, res.likes
            )
        } else {
            ProductEntity()
        }
    }
}