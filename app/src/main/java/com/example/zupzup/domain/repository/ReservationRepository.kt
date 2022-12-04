package com.example.zupzup.domain.repository

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {

    fun makeReservation(reservationModel: ReservationModel): Flow<DataResult<Long>>

    suspend fun sendNotificationTalk(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<Int>>

    suspend fun getMyReservationList(): Flow<DataResult<List<MyReservationModel>>>
}