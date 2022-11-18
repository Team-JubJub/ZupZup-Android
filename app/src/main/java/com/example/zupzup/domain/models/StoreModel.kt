package com.example.zupzup.domain.models


data class StoreModel(
    val storeID: Long,
    val headerInfo: StoreHeaderInfoModel,
    val address : String,
    val eventList : List<String>,
    val merchandiseList: List<MerchandiseModel>,
    val saleTime : Pair<Int,Int>
) {

    fun toDetailHeaderModel() : StoreDetailHeaderModel{
        return StoreDetailHeaderModel(
            headerInfo = headerInfo,
            address = address,
            eventList = eventList
        )
    }
}

