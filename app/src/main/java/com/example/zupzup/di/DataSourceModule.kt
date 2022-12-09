package com.example.zupzup.di

import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSourceImpl
import com.example.zupzup.data.datasource.reservation.ReservationLocalDataSource
import com.example.zupzup.data.datasource.reservation.ReservationLocalDataSourceImpl
import com.example.zupzup.data.datasource.reservation.ReservationRemoteDataSource
import com.example.zupzup.data.datasource.reservation.ReservationRemoteDataSourceImpl
import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.data.datasource.store.StoreDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLunaSoftDataSource(
        lunaSoftDataSourceImpl: LunaSoftDataSourceImpl
    ): LunaSoftDataSource

    @Binds
    @Singleton
    abstract fun bindStoreDataSource(
        storeDataSourceImpl: StoreDataSourceImpl
    ): StoreDataSource

    @Binds
    @Singleton
    abstract fun bindReservationRemoteDataSource(
        reservationRemoteDataSourceImpl: ReservationRemoteDataSourceImpl
    ): ReservationRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindReservationLocalDataSource(
        reservationLocalDataSourceImpl: ReservationLocalDataSourceImpl
    ): ReservationLocalDataSource
}