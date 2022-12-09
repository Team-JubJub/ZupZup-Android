package com.example.zupzup.domain.repository

import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel

interface ReservationRepository {

    suspend fun makeReservation(reservationModel: ReservationModel): Result<MyReservationModel>

    suspend fun sendNotificationTalk(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Result<Int>

    suspend fun getMyReservationList(): Result<List<MyReservationModel>>
}