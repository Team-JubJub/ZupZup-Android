package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMyReservationListUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : GetMyReservationListUseCase {
    override suspend operator fun invoke(): Flow<DataResult<List<MyReservationModel>>> {
        return flow {
            reservationRepository.getMyReservationList().onSuccess {
                emit(DataResult.Success(it))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}