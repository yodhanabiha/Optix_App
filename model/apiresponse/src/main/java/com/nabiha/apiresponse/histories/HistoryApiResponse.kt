package com.nabiha.apiresponse.histories

import com.nabiha.entity.HistoryEntity

data class HistoryApiResponse(
    val status: String,
    val results: HistoryEntity
)
