package com.nabiha.entity

data class CartEntity(
    val id: Long = 0,
    val product: ProductEntity = ProductEntity(),
    val userId:Long = 0,
    val total: Int = 0,
    val selected: Boolean = false,
)
