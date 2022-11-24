package com.example.zupzup.domain.models

data class ReservationModel(
    val reservationHeaderInfo: ReservationHeaderModel,
    val visitTime : Int,
    val customer : CustomerModel,
    val isAgree : Boolean
)
