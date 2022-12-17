package com.example.zupzup.data.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.zupzup.data.datasource.room.MyReservationEntity

@Dao
interface ReservationDao {

    @Query("SELECT * FROM MyReservationEntity")
    fun getMyReservationList() : List<MyReservationEntity>

    @Insert
    fun insertReservation(reservation: MyReservationEntity)
}