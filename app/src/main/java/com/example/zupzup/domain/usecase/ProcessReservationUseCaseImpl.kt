package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.net.UnknownHostException
import javax.inject.Inject

class ProcessReservationUseCaseImpl @Inject constructor(
    private val sendNotificationTalkUseCase: SendNotificationTalkUseCase,
    private val makeReservationOnFireBaseUseCase: MakeReservationOnFireBaseUseCase
) : ProcessReservationUseCase {
    override suspend fun invoke(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<Int>> {
        return flow {
            sendNotificationTalkUseCase.invoke(reservationModel, hostPhoneNumber).combine(
                makeReservationOnFireBaseUseCase.invoke(reservationModel)
            ) { postResult, fireBaseResult ->
                if (postResult is DataResult.Success && fireBaseResult is DataResult.Success) {
                    emit(DataResult.Success(0))
                }
                else {
                    emit(DataResult.Failure(UnknownHostException()))
                }
            }.collect()
        }
    }
}