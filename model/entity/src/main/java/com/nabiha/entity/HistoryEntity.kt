package com.nabiha.entity

import java.time.LocalDate
import java.time.LocalDateTime

data class HistoryEntity (
    val id: Long = 0,
    val price_item: Int=0,
    val total_item: Int=0,
    val total_price: Int=0,
    val purchase_date: String= "",
    val shipping:String="",
    val address: String="",
    val product:ProductEntity= ProductEntity(),
    val user: UserEntity=UserEntity()
)
