package com.example.zupzup.data.repository

import android.util.Log
import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.reservation.ReservationDataSource
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import com.example.zupzup.data.dto.mapper.DtoMapper
import com.example.zupzup.di.DataSourceModule
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    @DataSourceModule.ReservationRemote val reservationRemoteDataSource: ReservationDataSource,
    @DataSourceModule.ReservationLocal val reservationLocalDataSource: ReservationDataSource,
    private val lunaSoftDataSource: LunaSoftDataSource,
) : ReservationRepository {

    override fun makeReservation(reservationModel: ReservationModel): Flow<DataResult<Int>> {
        return flow {
            val reserveId = System.currentTimeMillis()
            val reservationDto = DtoMapper.reservationModelToDto(reservationModel, reserveId)
            val result = reservationRemoteDataSource.createReservation(reservationDto)
            result.onSuccess {
                val reservationEntity =
                    DtoMapper.reservationModelToEntity(reservationModel, reserveId)
                reservationLocalDataSource.createReservation(reservationEntity).onSuccess {
                    emit(DataResult.Success(it))
                }
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