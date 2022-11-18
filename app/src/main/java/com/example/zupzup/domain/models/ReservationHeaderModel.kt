package com.example.zupzup.domain.models

data class ReservationHeaderModel(
    val storeId: Long,
    val storeName: String,
    val storeAddress: String,
    val cartList: List<CartModel>,
    val saleTime: Pair<Int,Int>
)
