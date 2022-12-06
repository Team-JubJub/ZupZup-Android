package com.example.zupzup.data.repository

import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.reservation.ReservationDataSource
import com.example.zupzup.data.datasource.reservation.ReservationLocalDataSourceImpl
import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.data.dto.Reservation
import com.example.zupzup.data.dto.mapper.DtoMapper
import com.example.zupzup.di.DataSourceModule
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    @DataSourceModule.ReservationRemote private val reservationRemoteDataSource: ReservationDataSource,
    @DataSourceModule.ReservationLocal private val reservationLocalDataSource: ReservationDataSource,
    private val lunaSoftDataSource: LunaSoftDataSource,
    private val storeDataSource: StoreDataSource
) : ReservationRepository {

    override fun makeReservation(reservationModel: ReservationModel): Flow<DataResult<Long>> {
        return flow {
            val reserveId = System.currentTimeMillis()
            val reservationDto = DtoMapper.reservationModelToDto(reservationModel, reserveId)
            val result = reservationRemoteDataSource.createReservation(reservationDto)
            result.onSuccess {
                val reservationEntity =
                    DtoMapper.reservationModelToEntity(reservationModel, reserveId)
                reservationLocalDataSource.createReservation(reservationEntity).onSuccess {
                    storeDataSource.updateMerchandiseStock(
                        reservationModel.reservationHeaderInfo.storeId,
                        (reservationEntity as Reservation.ReservationEntity).cartList
                    ).onSuccess {
                        emit(DataResult.Success(it))
                    }
                }
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun sendNotificationTalk(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Flow<DataResult<Int>> {
        return flow {
            val messages = DtoMapper.getLunaSoftMessages(reservationModel, hostPhoneNumber)
            val result = lunaSoftDataSource.sendNotificationTalk(messages)
            result.onSuccess {
                emit(DataResult.Success(it.code))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMyReservationList(): Flow<DataResult<List<MyReservationModel>>> {
        return flow {
            val result =
                (reservationLocalDataSource as ReservationLocalDataSourceImpl).getMyReservationList()
            result.onSuccess {
                emit(DataResult.Success(it.map { reservation -> (reservation as Reservation.ReservationEntity).toModel() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}
