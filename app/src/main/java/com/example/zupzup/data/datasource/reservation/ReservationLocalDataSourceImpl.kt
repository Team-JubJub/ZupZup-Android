package com.example.zupzup.data.datasource.reservation

import android.util.Log
import com.example.zupzup.data.datasource.room.dao.ReservationDao
import com.example.zupzup.data.dto.Reservation
import javax.inject.Inject

class ReservationLocalDataSourceImpl @Inject constructor(
    private val dao: ReservationDao
) : ReservationDataSource {
    override suspend fun createReservation(reservation: Reservation): Result<Int> {
        return try {
            dao.insertReservation(reservation as Reservation.ReservationEntity)
            Result.success(1)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}