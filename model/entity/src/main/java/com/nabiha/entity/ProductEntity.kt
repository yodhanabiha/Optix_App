package com.nabiha.entity

data class ProductEntity(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val spec: String = "",
    val imageurl: String = "",
    val price: Int = 0,
    val likes: List<LikeEntity> = emptyList()
)
