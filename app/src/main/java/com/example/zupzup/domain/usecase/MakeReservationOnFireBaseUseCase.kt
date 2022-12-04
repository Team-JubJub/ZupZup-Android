package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import kotlinx.coroutines.flow.Flow

interface MakeReservationOnFireBaseUseCase {

    suspend operator fun invoke(
        reservationModel: ReservationModel
    ): Flow<DataResult<Long>>
}