package com.example.zupzup.data.datamodel

import com.example.zupzup.domain.models.MerchandiseModel

data class Merchandise(
    val itemId: Long = 0,
    val storeId: Long = 0,
    val itemName: String = "",
    val price: Int = 0,
    val discounted: Int = 0
) {

    fun toModel(): MerchandiseModel {
        return MerchandiseModel(
            itemId = itemId,
            storeId = storeId,
            itemName = itemName,
            price = price,
            discounted = discounted,
            discountRate = (100 - (discounted.toFloat() / price.toFloat() * 100)).toInt()
        )
    }
}
