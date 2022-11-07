package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.StoreHeaderInfoModel

data class StoreHeaderInfo(
    val name: String,
    val openTime: String,
) {

    fun toModel(storeID: Int, merchandiseList: List<Merchandise>): StoreHeaderInfoModel {
        return StoreHeaderInfoModel(
            storeID = storeID,
            name = name,
            openTime = openTime,
            maximumSales = merchandiseList.maxOf { it.sales }
        )
    }
}
