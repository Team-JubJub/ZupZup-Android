package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MakeReservationUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : MakeReservationUseCase {
    override suspend operator fun invoke(reservationModel: ReservationModel): Flow<DataResult<MyReservationModel>> {
        return flow {
            reservationRepository.makeReservation(reservationModel).onSuccess {
                emit(DataResult.Success(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}