package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.datasource.room.MyReservationEntity
import com.example.zupzup.data.datasource.room.dao.ReservationDao
import javax.inject.Inject

class ReservationLocalDataSourceImpl @Inject constructor(
    private val dao: ReservationDao
) : ReservationLocalDataSource {

    override suspend fun insertReservation(myReservationEntity: MyReservationEntity) =
        dao.insertReservation(myReservationEntity)

    override suspend fun getMyReservationEntityList(): List<MyReservationEntity> =
        dao.getMyReservationList()

}