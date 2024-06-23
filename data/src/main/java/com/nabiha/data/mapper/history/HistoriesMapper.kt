package com.nabiha.data.mapper.history

import com.nabiha.apiresponse.histories.HistoriesApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.HistoryEntity
import javax.inject.Inject

class HistoriesMapper @Inject constructor(): Mapper<HistoriesApiResponse, List<HistoryEntity>> {
    override fun mapFromApiResponse(type: HistoriesApiResponse): List<HistoryEntity> {
        return if (type.status == "SUCCESS") {
            type.results
        } else {
            emptyList()
        }
    }
}