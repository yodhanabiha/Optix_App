package com.nabiha.apiresponse.histories

import com.nabiha.entity.HistoryEntity

data class HistoriesApiResponse (
    val status: String,
    val results: List<HistoryEntity>
)