package com.example.zupzup.data.dto

data class Cart(
    val itemId : Long,
    val storeId : Long,
    val name : String,
    val amount : Int,
    val salesPrice : Int
)
