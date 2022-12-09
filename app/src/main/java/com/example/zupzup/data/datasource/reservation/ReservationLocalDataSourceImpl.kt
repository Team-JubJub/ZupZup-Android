package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.datasource.room.dao.ReservationDao
import com.example.zupzup.data.dto.reservation.ReservationEntity
import javax.inject.Inject

class ReservationLocalDataSourceImpl @Inject constructor(
    private val dao: ReservationDao
) : ReservationLocalDataSource {

    override suspend fun insertReservation(reservation: ReservationEntity) =
        dao.insertReservation(reservation)


    override suspend fun getMyReservationList(): List<ReservationEntity> =
        dao.getMyReservationList()

}