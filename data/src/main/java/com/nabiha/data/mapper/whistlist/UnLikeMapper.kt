package com.nabiha.data.mapper.whistlist

import com.nabiha.apiresponse.likes.UnlikeApiResponse
import com.nabiha.data.utils.Mapper
import javax.inject.Inject

class UnLikeMapper @Inject constructor() : Mapper<UnlikeApiResponse, String> {
    override fun mapFromApiResponse(type: UnlikeApiResponse): String {
        return if (type.status == "SUCCESS"){
            type.results
        }else{
            "unlike failed"
        }
    }
}