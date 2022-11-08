package com.example.zupzup.domain.models

data class MerchandiseModel (
    val itemId : Long = 0,
    val storeId : Long = 0,
    val itemName : String = "",
    val price : Int = 0,
    val discountRate : Int = 0
)