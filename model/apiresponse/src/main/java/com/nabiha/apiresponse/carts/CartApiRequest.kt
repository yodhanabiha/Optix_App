package com.nabiha.apiresponse.carts

data class CartApiRequest(
    val productId:Long,
    val userId:Long,
    val total:Int,
    val selected:Boolean,
)
