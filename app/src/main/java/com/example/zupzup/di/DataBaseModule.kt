package com.example.zupzup.di

import android.content.Context
import androidx.room.Room
import com.example.zupzup.data.datasource.room.database.ReservationDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    private const val RESERVE_DB_NAME = "reservation_db"

    @Provides
    @Singleton
    fun provideReservationDataBase(
        @ApplicationContext context: Context,
    ): ReservationDataBase =
        Room.databaseBuilder(
            context,
            ReservationDataBase::class.java,
            RESERVE_DB_NAME
        ).build()

    @Provides
    fun provideReservationDao(reservationDataBase: ReservationDataBase) =
        reservationDataBase.reservationDao()
}

