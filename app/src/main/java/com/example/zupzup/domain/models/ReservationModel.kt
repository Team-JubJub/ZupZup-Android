package com.example.zupzup.domain.models

data class ReservationModel(
    val reservationHeaderInfo: ReservationHeaderModel,
    val visitTime : String,
    val customer : CustomerModel?
)
