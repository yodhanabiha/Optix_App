package com.nabiha.data.mapper.cart

import com.nabiha.apiresponse.carts.CartApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.CartEntity
import javax.inject.Inject

class CartMapper @Inject constructor(): Mapper<CartApiResponse, CartEntity> {
    override fun mapFromApiResponse(type: CartApiResponse): CartEntity {
        return if (type.status == "SUCCESS") {
            val res = type.results
            CartEntity(
                res.id, res.product, res.userId, res.total, res.selected
            )
        } else {
            CartEntity()
        }
    }
}