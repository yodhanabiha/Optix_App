package com.nabiha.apiresponse.products

import com.nabiha.entity.ProductEntity

data class ProductsApiResponse(
    val status: String,
    val results: List<ProductEntity>,
)