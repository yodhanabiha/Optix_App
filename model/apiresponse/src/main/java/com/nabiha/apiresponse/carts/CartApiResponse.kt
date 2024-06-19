package com.nabiha.apiresponse.carts

import com.nabiha.entity.CartEntity

data class CartApiResponse(
    val status: String,
    val results: CartEntity,
)
