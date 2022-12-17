package com.example.zupzup.data.dto

import com.example.zupzup.domain.models.CartModel

data class Cart(
    val itemId : Long = 0,
    val storeId : Long = 0,
    val name : String = "",
    val amount : Int = 0,
    val salesPrice : Int = 0
) {
    fun toModel(): CartModel {
        return CartModel(
            itemId = itemId,
            storeId = storeId,
            itemName = name,
            amount = amount,
            salesPrice = salesPrice
        )
    }
}