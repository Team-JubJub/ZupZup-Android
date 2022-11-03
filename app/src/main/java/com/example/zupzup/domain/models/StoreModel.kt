package com.example.zupzup.domain.models


data class StoreModel(
    val storeID: Int,
    val headerInfo: StoreHeaderInfoModel,
    val address : String,
    val eventList : List<String>,
    val merchandiseList: List<MerchandiseModel>
) {

    fun toDetailHeaderModel() : StoreDetailHeaderModel{
        return StoreDetailHeaderModel(
            headerInfo = headerInfo,
            address = address,
            eventList = eventList
        )
    }
}

