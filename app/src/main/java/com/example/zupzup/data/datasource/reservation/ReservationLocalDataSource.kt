package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.dto.reservation.ReservationEntity

interface ReservationLocalDataSource {

    suspend fun insertReservation(reservation: ReservationEntity)

    suspend fun getMyReservationList(): List<ReservationEntity>
}