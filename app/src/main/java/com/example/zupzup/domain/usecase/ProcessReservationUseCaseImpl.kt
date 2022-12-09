package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProcessReservationUseCaseImpl @Inject constructor(
    private val sendNotificationTalkUseCase: SendNotificationTalkUseCase,
    private val makeReservationUseCase: MakeReservationUseCase
) : ProcessReservationUseCase {
    override suspend fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<MyReservationModel>> {
        return flow {
            makeReservationUseCase.invoke(reservationModel)
                .collect { makeReservationResult ->
                    when (makeReservationResult) {
                        is DataResult.Success -> {
                            sendNotificationTalkUseCase.invoke(reservationModel, hostPhoneNumber)
                                .onSuccess {
                                    emit(DataResult.Success(makeReservationResult.data))
                                }.onFailure {
                                    emit(DataResult.Failure(it))
                                }
                        }
                        is DataResult.Failure -> {

                        }
                    }
                }
        }
    }
}