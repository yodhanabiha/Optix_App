package com.nabiha.data.mapper.cart

import com.nabiha.apiresponse.carts.CartsApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.CartEntity
import javax.inject.Inject

class CartsMapper @Inject constructor(): Mapper<CartsApiResponse, List<CartEntity>> {
    override fun mapFromApiResponse(type: CartsApiResponse): List<CartEntity> {
        return if (type.status == "SUCCESS") {
            type.results
        } else {
            emptyList()
        }
    }
}