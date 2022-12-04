package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import kotlinx.coroutines.flow.Flow

interface GetMyReservationListUseCase {

    suspend operator fun invoke(): Flow<DataResult<List<MyReservationModel>>>
}