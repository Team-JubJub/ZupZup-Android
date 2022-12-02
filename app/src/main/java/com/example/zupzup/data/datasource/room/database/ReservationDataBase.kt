package com.example.zupzup.data.datasource.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zupzup.data.datasource.room.dao.ReservationDao
import com.example.zupzup.data.datasource.room.typeconverter.TypeConverter
import com.example.zupzup.data.dto.Reservation

@Database(entities = [Reservation.ReservationEntity::class], version = 1)
@TypeConverters(
    value = [
        TypeConverter::class
    ]
)
abstract class ReservationDataBase : RoomDatabase() {
    abstract fun reservationDao(): ReservationDao
}