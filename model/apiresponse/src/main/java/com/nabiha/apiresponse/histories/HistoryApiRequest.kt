package com.nabiha.apiresponse.histories

import com.nabiha.entity.ProductEntity
import com.nabiha.entity.UserEntity
import java.time.LocalDateTime

data class HistoriesApiRequest(
    val histories: List<HistoryApiRequest>
)


data class HistoryApiRequest(
    val price_item: Int,
    val total_item: Int,
    val total_price: Int,
    val shipping:String,
    val address: String,
    val product_id: Long,
    val user_id: Long
)
