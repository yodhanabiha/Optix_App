package com.nabiha.apiresponse.products

import com.nabiha.entity.ProductEntity

data class ProductApiResponse(
    val status: String,
    val results: ProductEntity,
)
