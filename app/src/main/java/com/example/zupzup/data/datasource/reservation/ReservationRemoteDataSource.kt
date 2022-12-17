package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.dto.reservation.ReservationDto

interface ReservationRemoteDataSource {

    suspend fun createReservation(reservation: ReservationDto)

    suspend fun getMyReservationList(reservationIdList : List<Long>) : List<ReservationDto>
}