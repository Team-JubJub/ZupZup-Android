package com.example.zupzup.domain.models


data class StoreModel(
    val storeId: Long,
    val name: String,
    val openTime: String,
    val hostPhoneNumber : String,
    val location: Pair<Double, Double>,
    val address: String,
    val eventList: List<String>,
    val merchandiseList: List<MerchandiseModel>,
    val saleTime: Pair<Int, Int>
) {

    fun toDetailHeaderModel(): StoreDetailHeaderModel {
        return StoreDetailHeaderModel(
            name = name,
            address = address,
            openTime = openTime,
            location = location,
            eventList = eventList,
            saleTime = saleTime
        )
    }
}

