package com.nabiha.apiresponse.likes

import com.nabiha.entity.LikeEntity

data class LikeApiResponse(
    val status: String,
    val results: LikeEntity,
)
