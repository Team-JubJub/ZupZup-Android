package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.models.ReservationModel

interface SendNotificationTalkUseCase {

    suspend operator fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Result<Int>
}