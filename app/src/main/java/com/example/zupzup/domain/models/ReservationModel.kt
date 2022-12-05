package com.example.zupzup.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationModel(
    val reservationHeaderInfo: ReservationHeaderModel,
    val visitTime : Int,
    val customer : CustomerModel,
    val isAgree : Boolean
) : Parcelable
