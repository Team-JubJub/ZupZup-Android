package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.datasource.room.MyReservationEntity

interface ReservationLocalDataSource {

    suspend fun insertReservation(myReservationEntity: MyReservationEntity)

    suspend fun getMyReservationEntityList() : List<MyReservationEntity>
}