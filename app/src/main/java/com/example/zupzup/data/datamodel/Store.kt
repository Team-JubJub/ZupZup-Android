package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.domain.models.StoreModel

data class Store(
    val storeId: Int,
    val headerInfo : StoreHeaderInfo,
    val merchandiseList : List<Merchandise>
) {
    fun toModel(): StoreModel {
        return StoreModel(
            storeId = storeId,
            headerInfo = toHeaderModel(),
            merchandiseList = merchandiseList.map { it.toModel() }
        )
    }
    fun toHeaderModel(): StoreHeaderModel {
        return StoreHeaderModel(
            storeId = storeId,
            storeAddress = headerInfo.storeAddress,
            storeType = headerInfo.storeType,
            storeName = headerInfo.storeName,
            storeSalesInfo = headerInfo.storeSalesInfo,
            storeOpenTime = headerInfo.storeOpenTime,
            storeEventList = headerInfo.storeEventList
        )
    }
}