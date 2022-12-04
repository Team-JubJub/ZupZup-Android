package com.example.zupzup.data.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.zupzup.data.dto.Reservation

@Dao
interface ReservationDao {

    @Query("SELECT * FROM ReservationEntity")
    fun getMyReservationList(): List<Reservation.ReservationEntity>

    @Insert
    fun insertReservation(reservation: Reservation.ReservationEntity) : Long
}