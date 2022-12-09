package com.example.zupzup.data.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.zupzup.data.dto.reservation.ReservationEntity

@Dao
interface ReservationDao {

    @Query("SELECT * FROM ReservationEntity ORDER BY reserve_id DESC")
    fun getMyReservationList(): List<ReservationEntity>

    @Insert
    fun insertReservation(reservation: ReservationEntity)
}