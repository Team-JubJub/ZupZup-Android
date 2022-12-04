package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import kotlinx.coroutines.flow.Flow

interface SendNotificationTalkUseCase {

    suspend operator fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<Int>>
}