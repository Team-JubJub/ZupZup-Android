package com.example.zupzup.domain.models

data class MerchandiseModel(
    val itemId: Long = 0,
    val storeId: Long = 0,
    val itemName: String = "",
    val price: Int = 0,
    val imgUrl : String = "",
    val discounted: Int = 0
) {

    fun toCartModel(amount: Int): CartModel {
        return CartModel(
            itemId = itemId,
            storeId = storeId,
            itemName = itemName,
            salesPrice = discounted,
            amount = amount
        )
    }
}

