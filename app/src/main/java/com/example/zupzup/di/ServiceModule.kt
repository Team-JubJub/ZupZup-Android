package com.example.zupzup.di

import com.example.zupzup.data.api.LunaSoftService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideLunaSoftService(retrofit: Retrofit): LunaSoftService {
        return retrofit.create(LunaSoftService::class.java)
    }
}