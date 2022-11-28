package com.example.zupzup.data.datasource.remote.firebase

import com.example.zupzup.data.dto.Reservation
import kotlinx.coroutines.flow.Flow

interface ReservationDataSource {

    suspend fun createReservation(reservation: Reservation): Result<Int>
}