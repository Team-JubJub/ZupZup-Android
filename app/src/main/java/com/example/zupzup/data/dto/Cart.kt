package com.example.zupzup.data.dto

import com.example.zupzup.domain.models.CartModel

data class Cart(
    val itemId : Long,
    val storeId : Long,
    val name : String,
    val amount : Int,
    val salesPrice : Int
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