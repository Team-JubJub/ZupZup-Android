package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.StoreModel

data class Store(
    val storeID: Int,
    val headerInfo: StoreHeaderInfo,
    val address: String,
    val eventList: List<String>,
    val merchandiseList: List<Merchandise>
) {
    fun toModel(): StoreModel {
        return StoreModel(
            storeID = storeID,
            headerInfo = headerInfo.toModel(storeID, merchandiseList),
            address = address,
            eventList = eventList,
            merchandiseList = merchandiseList.map { it.toModel() }
        )
    }
}