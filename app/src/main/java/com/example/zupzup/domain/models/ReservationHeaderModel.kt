package com.example.zupzup.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationHeaderModel(
    val storeId: Long,
    val storeName: String,
    val storeAddress: String,
    val cartList: List<CartModel>,
    val saleTime: Pair<Int, Int>
) : Parcelable
