package com.nabiha.apiresponse.likes

import com.nabiha.entity.LikeEntity

data class LikesApiResponse(
    val status: String,
    val results: List<LikeEntity>,
)
