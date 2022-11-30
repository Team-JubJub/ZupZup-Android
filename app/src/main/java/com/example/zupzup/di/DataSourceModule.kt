package com.example.zupzup.di

import com.example.zupzup.data.datasource.reservation.ReservationLocalDataSourceImpl
import com.example.zupzup.data.datasource.reservation.ReservationDataSource
import com.example.zupzup.data.datasource.reservation.ReservationRemoteDataSourceImpl
import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.data.datasource.store.StoreDataSourceImpl
import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReservationRemote

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReservationLocal

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
    @ReservationRemote
    abstract fun bindReservationRemoteDataSource(
        reservationRemoteDataSourceImpl: ReservationRemoteDataSourceImpl
    ): ReservationDataSource

    @Binds
    @Singleton
    @ReservationLocal
    abstract fun bindReservationLocalDataSource(
        reservationLocal: ReservationLocalDataSourceImpl
    ): ReservationDataSource
}