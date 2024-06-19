package com.nabiha.entity

data class LikeEntity(
    var id: Long = 0,
    val product: ProductEntity = ProductEntity(),
    val user: UserEntity = UserEntity()
)


