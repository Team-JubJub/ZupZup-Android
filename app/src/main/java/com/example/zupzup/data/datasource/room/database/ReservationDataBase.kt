package com.example.zupzup.data.datasource.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zupzup.data.datasource.room.MyReservationEntity
import com.example.zupzup.data.datasource.room.dao.ReservationDao

@Database(entities = [MyReservationEntity::class], version = 1)
abstract class ReservationDataBase : RoomDatabase() {
    abstract fun reservationDao(): ReservationDao
}