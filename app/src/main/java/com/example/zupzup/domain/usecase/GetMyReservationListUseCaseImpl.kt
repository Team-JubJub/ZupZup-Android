package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyReservationListUseCaseImpl @Inject constructor(
    private val reservationRepository: ReservationRepository
) : GetMyReservationListUseCase {
    override suspend operator fun invoke(): Flow<DataResult<List<MyReservationModel>>> {
        return reservationRepository.getMyReservationList()
    }
}