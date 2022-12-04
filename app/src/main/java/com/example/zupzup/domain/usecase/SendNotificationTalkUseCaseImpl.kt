package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SendNotificationTalkUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : SendNotificationTalkUseCase {
    override suspend fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<Int>> {
        return reservationRepository.sendNotificationTalk(reservationModel, hostPhoneNumber)
    }
}