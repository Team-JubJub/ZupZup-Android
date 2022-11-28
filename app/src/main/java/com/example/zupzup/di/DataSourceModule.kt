package com.example.zupzup.di

import com.example.zupzup.data.datasource.remote.firebase.ReservationDataSource
import com.example.zupzup.data.datasource.remote.firebase.ReservationDataSourceImpl
import com.example.zupzup.data.datasource.remote.firebase.StoreDataSource
import com.example.zupzup.data.datasource.remote.firebase.StoreDataSourceImpl
import com.example.zupzup.data.datasource.remote.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.remote.lunasoft.LunaSoftDataSourceImpl
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
    abstract fun bindReservationDataSource(
        reservationDataSourceImpl: ReservationDataSourceImpl
    ): ReservationDataSource
}