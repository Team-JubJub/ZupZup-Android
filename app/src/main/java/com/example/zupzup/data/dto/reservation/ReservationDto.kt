package com.example.zupzup.data.dto.reservation

import com.example.zupzup.data.dto.Cart
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.MyReservationModel

data class ReservationDto(
    val reserveId: Long = 0,
    val storeId: Long = 0,
    val state: String = "",
    val cartList: List<Cart> = listOf(),
    val customerName: String = "",
    val customerPhone: String = "",
    val visitTime: Int = 0
) {
    fun toMyReservationModel(storeName: String, storeAddress: String): MyReservationModel {
        return MyReservationModel(
            reserveId = reserveId,
            storeName = storeName,
            storeAddress = storeAddress,
            visitTime = visitTime,
            cartList = cartList.map { it.toModel() },
            customer = CustomerModel(customerName, customerPhone),
            state = state
        )
    }
}
