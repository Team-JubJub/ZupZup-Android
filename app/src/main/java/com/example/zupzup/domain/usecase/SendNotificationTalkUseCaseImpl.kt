package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendNotificationTalkUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : SendNotificationTalkUseCase {
    override fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<Int>> {
        return flow {
            val result =
                reservationRepository.sendNotificationTalk(reservationModel, hostPhoneNumber)
            result.onSuccess {
                when (it.code) {
                    0 -> emit(DataResult.Success(0))
                }
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }
    }
}