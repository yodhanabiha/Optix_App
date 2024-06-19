package com.nabiha.data.mapper.whistlist

import com.nabiha.apiresponse.likes.LikesApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.LikeEntity
import javax.inject.Inject

class WhistListMapper @Inject constructor() : Mapper<LikesApiResponse, List<LikeEntity>> {
    override fun mapFromApiResponse(type: LikesApiResponse): List<LikeEntity> {
        return if (type.status == "SUCCESS"){
            type.results
        }else{
            emptyList()
        }
    }

}