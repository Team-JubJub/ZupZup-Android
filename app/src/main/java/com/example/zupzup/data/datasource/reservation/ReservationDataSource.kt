package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.dto.Reservation

interface ReservationDataSource {

    suspend fun createReservation(reservation: Reservation): Result<Long>
}