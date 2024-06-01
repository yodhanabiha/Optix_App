package com.nabiha.apiresponse.carts

import com.nabiha.entity.CartEntity

data class CartsApiResponse (
    val status: String,
    val results: List<CartEntity>
)