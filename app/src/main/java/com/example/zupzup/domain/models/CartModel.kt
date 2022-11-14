package com.example.zupzup.domain.models

data class CartModel(
    val itemId: Long = 0,
    val storeId: Long = 0,
    val itemName: String = "",
    val salesPrice: Int = 0,
    val amount : Int = 0
)
