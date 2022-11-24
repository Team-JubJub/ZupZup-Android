package com.example.zupzup.data.dto

data class Reservation(
    val reserveId: Long,
    val storeId: Long,
    val state: String,
    val cartList: List<Cart>,
    val customerName: String,
    val customerPhone: String,
    val visitTime: Int
)