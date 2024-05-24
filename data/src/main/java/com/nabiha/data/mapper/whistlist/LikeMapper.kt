package com.nabiha.data.mapper.whistlist

import com.nabiha.apiresponse.likes.LikeApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.LikeEntity
import javax.inject.Inject

class LikeMapper @Inject constructor() : Mapper<LikeApiResponse, LikeEntity> {
    override fun mapFromApiResponse(type: LikeApiResponse): LikeEntity {
        return if (type.status == "SUCCESS") {
            val res = type.results
            LikeEntity(res.id, res.product_id, res.user_id)
        } else {
            LikeEntity()
        }
    }
}