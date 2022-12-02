package com.example.zupzup.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Reservation {
    data class ReservationDto(
        val reserveId: Long,
        val storeId: Long,
        val state: String,
        val cartList: List<Cart>,
        val customerName: String,
        val customerPhone: String,
        val visitTime: Int
    ) : Reservation()

    @Entity
    data class ReservationEntity(
        @PrimaryKey @ColumnInfo(name = "reserve_id") val reserveId: Long,
        @ColumnInfo(name = "store_name") val storeName: String,
        @ColumnInfo(name = "store_address") val storeAddress: String,
        @ColumnInfo(name = "cart_list") val cartList: List<Cart>,
        @ColumnInfo(name = "customer_name") val customerName: String,
        @ColumnInfo(name = "customer_phoneNumber") val customerPhone: String,
        @ColumnInfo(name = "visit_time") val visitTime: Int
    ) : Reservation()
}
