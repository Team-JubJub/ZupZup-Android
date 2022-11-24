package com.example.zupzup.di

import com.example.zupzup.domain.usecase.*
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

    @Binds
    @ViewModelScoped
    abstract fun bindGetStoreListUseCase(
        getStoreListUseCaseImpl: GetStoreListUseCaseImpl
    ): GetStoreListUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetStoreDetailUseCase(
        getStoreDetailUseCaseImpl: GetStoreDetailUseCaseImpl
    ): GetStoreDetailUseCase
}