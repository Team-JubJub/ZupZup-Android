package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.StoreHeaderInfoModel

data class StoreHeaderInfo(
    val storeId : Long = 0,
    val name: String= "",
    val openTime: String = "",
) {

    fun toModel(storeId: Long, merchandiseList: List<Merchandise>): StoreHeaderInfoModel {
        return StoreHeaderInfoModel(
            storeID = storeId,
            name = name,
            openTime = openTime,
            maximumSales = merchandiseList.maxOf { it.discountRate }
        )
    }
}
