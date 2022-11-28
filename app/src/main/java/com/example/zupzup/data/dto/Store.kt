package com.example.zupzup.data.dto

import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel

data class Store(
    val storeId: Long = 0,
    val name: String = "",
    val openTime: String = "",
    val hostPhoneNumber : String = "",
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val address: String = "",
    val eventList: List<String> = listOf(),
    val merchandiseList: List<Merchandise> = listOf(),
    val saleTimeStart: Int = 0,
    val saleTimeEnd: Int = 0
) {

    fun toModel(): StoreModel {
        return StoreModel(
            storeId = storeId,
            name = name,
            openTime = openTime,
            hostPhoneNumber = hostPhoneNumber,
            location = Pair(latitude, longitude),
            address = address,
            eventList = eventList,
            merchandiseList = merchandiseList.map { it.toModel() },
            saleTime = Pair(saleTimeStart, saleTimeEnd)
        )
    }

    fun toHeaderInfoModel(): StoreHeaderInfoModel {
        return StoreHeaderInfoModel(
            storeId = storeId,
            name = name,
            openTime = openTime,
            maximumSales = merchandiseList.maxOf { (100 - (it.discounted.toFloat() / it.price.toFloat() * 100)).toInt() }
        )
    }
}