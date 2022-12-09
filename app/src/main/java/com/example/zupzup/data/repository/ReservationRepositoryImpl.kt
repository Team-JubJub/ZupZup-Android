package com.example.zupzup.data.repository

import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.reservation.ReservationLocalDataSource
import com.example.zupzup.data.datasource.reservation.ReservationRemoteDataSource
import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.data.dto.mapper.DtoMapper
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationRemoteDataSource: ReservationRemoteDataSource,
    private val reservationLocalDataSource: ReservationLocalDataSource,
    private val lunaSoftDataSource: LunaSoftDataSource,
    private val storeDataSource: StoreDataSource
) : ReservationRepository {

    override suspend fun makeReservation(reservationModel: ReservationModel): Result<MyReservationModel> {
        return try {
            val reserveId = System.currentTimeMillis()
            val reservationDto = DtoMapper.reservationModelToDto(reservationModel, reserveId)
            val reservationEntity = DtoMapper.reservationModelToEntity(reservationModel, reserveId)
            reservationRemoteDataSource.createReservation(reservationDto)
            reservationLocalDataSource.insertReservation(reservationEntity)
            storeDataSource.updateMerchandiseStock(
                reservationModel.reservationHeaderInfo.storeId, reservationEntity.cartList
            )
            Result.success(reservationEntity.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /*
    0. 인터넷 연결 안 돼 있으면 catch
    1. base url 잘못됨 --> UnKnownHttpException
    2. path 잘못됨 --> 404
     */
    override suspend fun sendNotificationTalk(
        reservationModel: ReservationModel,
        hostPhoneNumber: String
    ): Result<Int> {
        return try {
            val messages = DtoMapper.getLunaSoftMessages(reservationModel, hostPhoneNumber)
            val result = lunaSoftDataSource.sendNotificationTalk(messages)
            if (result.isSuccessful) {
                when (result.body()?.code) {
                    0 -> Result.success(0)
                    else -> {
                        Result.success(1)
                        // LunaSoftResponse 별 예외처리
                    }
                }
            } else {
                Result.success(result.code())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMyReservationList(): Result<List<MyReservationModel>> {
        return try {
            Result.success(
                reservationLocalDataSource.getMyReservationList().map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}
