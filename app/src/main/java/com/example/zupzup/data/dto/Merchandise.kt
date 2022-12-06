package com.example.zupzup.data.dto

import com.example.zupzup.domain.models.MerchandiseModel

data class Merchandise(
    val itemId: Long = 0,
    val storeId: Long = 0,
    val imgUrl: String = "",
    val itemName: String = "",
    val price: Int = 0,
    val discounted: Int = 0,
    var stock: Int = 0
) {

    fun toModel(): MerchandiseModel {
        return MerchandiseModel(
            itemId = itemId,
            storeId = storeId,
            imgUrl = imgUrl,
            itemName = itemName,
            price = price,
            discounted = discounted,
            stock = stock
        )
    }
}
