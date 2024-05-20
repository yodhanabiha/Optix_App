package com.nabiha.apiresponse.products

import com.nabiha.entity.ProductEntity

data class ProductsApiResponse(
    val success: String,
    val result: List<ProductEntity>,
)