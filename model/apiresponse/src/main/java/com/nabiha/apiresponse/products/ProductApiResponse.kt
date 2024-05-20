package com.nabiha.apiresponse.products

import com.nabiha.entity.ProductEntity

data class ProductApiResponse(
    val success: String,
    val result: ProductEntity,
)
