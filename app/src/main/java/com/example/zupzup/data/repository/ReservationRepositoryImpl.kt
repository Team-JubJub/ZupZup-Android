package com.example.zupzup.data.repository

import android.util.Log
import com.example.zupzup.data.datasource.remote.firebase.ReservationDataSource
import com.example.zupzup.data.datasource.remote.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import com.example.zupzup.data.mapper.DtoMapper
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationDataSource: ReservationDataSource,
    private val lunaSoftDataSource: LunaSoftDataSource
) : ReservationRepository {

    override fun makeReservation(reservationModel: ReservationModel): Flow<DataResult<Int>> {
        return flow {
            val reservation = DtoMapper.transFormReservationDto(reservationModel)
            val result = reservationDataSource.createReservation(reservation)
            result.onSuccess {
                emit(DataResult.Success(it))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }
    }

    override suspend fun sendNotificationTalk(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Result<LunaSoftResponse> {
        val messages = DtoMapper.getLunaSoftMessages(reservationModel, hostPhoneNumber)
        return lunaSoftDataSource.sendNotificationTalk(messages)
    }
}