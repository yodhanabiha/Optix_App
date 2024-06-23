package com.nabiha.data.mapper.history

import com.nabiha.apiresponse.histories.HistoryApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.HistoryEntity
import javax.inject.Inject

class HistoryMapper @Inject constructor(): Mapper<HistoryApiResponse, HistoryEntity> {
    override fun mapFromApiResponse(type: HistoryApiResponse): HistoryEntity {
        return if (type.status == "SUCCESS") {
            val res = type.results
            HistoryEntity(
                id = res.id,
                user = res.user,
                purchase_date = res.purchase_date,
                price_item = res.price_item,
                total_item = res.total_item,
                total_price = res.total_price,
                shipping = res.shipping,
                address = res.address,
                product = res.product
            )
        } else {
            HistoryEntity()
        }
    }
}