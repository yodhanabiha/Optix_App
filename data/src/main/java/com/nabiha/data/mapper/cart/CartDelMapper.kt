package com.nabiha.data.mapper.cart

import com.nabiha.apiresponse.carts.CartApiDelResponse
import com.nabiha.apiresponse.likes.UnlikeApiResponse
import com.nabiha.data.utils.Mapper
import javax.inject.Inject

class CartDelMapper @Inject constructor(): Mapper<CartApiDelResponse, String> {
    override fun mapFromApiResponse(type: CartApiDelResponse): String {
        return if (type.status == "SUCCESS"){
            type.results
        }else{
            "delete cart failed!"
        }
    }
}