package com.example.zupzup.di

import com.example.zupzup.domain.usecase.MakeReservationOnFireBaseUseCase
import com.example.zupzup.domain.usecase.MakeReservationOnFireBaseUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMakeReservationOnFireBaseUseCase(
        makeReservationOnFireBaseUseCaseImpl: MakeReservationOnFireBaseUseCaseImpl
    ): MakeReservationOnFireBaseUseCase
}