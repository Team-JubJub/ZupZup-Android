package com.example.zupzup.di

import com.example.zupzup.data.datasource.StoreDataSource
import com.example.zupzup.data.datasource.StoreDataSourceImpl
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
    abstract fun bindStoreDataSource(
        storeDataSourceImpl: StoreDataSourceImpl
    ): StoreDataSource
}