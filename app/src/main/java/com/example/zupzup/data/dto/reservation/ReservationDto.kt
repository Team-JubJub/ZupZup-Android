package com.example.zupzup.data.dto.reservation

import com.example.zupzup.data.dto.Cart

data class ReservationDto(
    val reserveId: Long,
    val storeId: Long,
    val state: String,
    val cartList: List<Cart>,
    val customerName: String,
    val customerPhone: String,
    val visitTime: Int
)
