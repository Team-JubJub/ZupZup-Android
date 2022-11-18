package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.StoreModel

data class Store(
    val storeId: Long = 0,
    val headerInfo: StoreHeaderInfo = StoreHeaderInfo(0, ""),
    val address: String = "",
    val eventList: List<String> = listOf(),
    val merchandiseList: List<Merchandise> = listOf(),
    val saleTime: SaleTime = SaleTime(0,0)
) {

    data class SaleTime(
        val start: Int = 0,
        val end: Int = 0
    )

    fun toModel(): StoreModel {
        return StoreModel(
            storeID = storeId,
            headerInfo = headerInfo.toModel(storeId, merchandiseList),
            address = address,
            eventList = eventList,
            merchandiseList = merchandiseList.map { it.toModel() },
            saleTime = Pair(saleTime.start, saleTime.end)
        )
    }
}