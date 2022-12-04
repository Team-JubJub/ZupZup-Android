package com.example.zupzup.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyReservationModel(
    val reserveId: Long,
    val storeName: String,
    val storeAddress: String,
    val visitTime: Int,
    val cartList: List<CartModel>,
    val customer: CustomerModel
) : Parcelable