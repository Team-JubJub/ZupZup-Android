package com.example.zupzup.data.mapper

import com.example.zupzup.data.dto.Cart
import com.example.zupzup.data.dto.Reservation
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.ReservationModel

object DtoMapper {

    fun transFormReservationDto(reservationModel: ReservationModel): Reservation {
        return Reservation(
            System.currentTimeMillis(),
            reservationModel.reservationHeaderInfo.storeId,
            "NEW",
            reservationModel.reservationHeaderInfo.cartList.map { it.toDto() },
            reservationModel.customer.name,
            reservationModel.customer.phoneNumber,
            reservationModel.visitTime
        )
    }

    private fun CartModel.toDto(): Cart {
        return Cart(
            itemId = itemId,
            storeId = storeId,
            name = itemName!!,
            amount = amount,
            salesPrice = salesPrice
        )
    }
}