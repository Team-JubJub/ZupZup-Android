package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import javax.inject.Inject

class SendNotificationTalkUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : SendNotificationTalkUseCase {
    override suspend fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Result<Int> {
        return reservationRepository.sendNotificationTalk(reservationModel, hostPhoneNumber)
    }
}