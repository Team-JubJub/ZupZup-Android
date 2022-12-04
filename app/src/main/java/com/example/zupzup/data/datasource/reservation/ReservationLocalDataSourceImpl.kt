package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.datasource.room.dao.ReservationDao
import com.example.zupzup.data.dto.Reservation
import javax.inject.Inject

class ReservationLocalDataSourceImpl @Inject constructor(
    private val dao: ReservationDao
) : ReservationDataSource {

    override suspend fun createReservation(reservation: Reservation): Result<Long> = runCatching {
        dao.insertReservation(reservation as Reservation.ReservationEntity)
    }

    suspend fun getMyReservationList(): Result<List<Reservation>> = runCatching {
        dao.getMyReservationList()
    }
}