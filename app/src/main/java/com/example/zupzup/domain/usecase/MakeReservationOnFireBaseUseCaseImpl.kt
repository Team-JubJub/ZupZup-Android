package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MakeReservationOnFireBaseUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : MakeReservationOnFireBaseUseCase {
    override operator fun invoke(reservationModel: ReservationModel): Flow<DataResult<Int>> {
        return flow {
            reservationRepository.makeReservation(reservationModel).collect {
                emit(it)
            }
        }
    }
}