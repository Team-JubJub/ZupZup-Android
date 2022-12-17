package com.example.zupzup.data.repository

import android.content.Context
import com.example.zupzup.data.datasource.lunasoft.LunaSoftDataSource
import com.example.zupzup.data.datasource.reservation.ReservationLocalDataSource
import com.example.zupzup.data.datasource.reservation.ReservationRemoteDataSource
import com.example.zupzup.data.datasource.room.MyReservationEntity
import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.data.dto.mapper.DtoMapper
import com.example.zupzup.data.dto.mapper.DtoMapper.toDto
import com.example.zupzup.domain.NetworkManager
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.repository.ReservationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.UnknownHostException
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationRemoteDataSource: ReservationRemoteDataSource,
    private val reservationLocalDataSource: ReservationLocalDataSource,
    private val lunaSoftDataSource: LunaSoftDataSource,
    private val storeDataSource: StoreDataSource,
    @ApplicationContext private val context: Context
) : ReservationRepository {

    private fun getMyReservationEntity(
        reservationModel: ReservationModel,
        reserveId: Long
    ): MyReservationEntity {
        return MyReservationEntity(
            reserveId,
            reservationModel.reservationHeaderInfo.storeName,
            reservationModel.reservationHeaderInfo.storeAddress
        )
    }

    override suspend fun makeReservation(reservationModel: ReservationModel): Result<MyReservationModel> {
        return try {
            if (!NetworkManager(context).isNetworkConnected()) {
                throw UnknownHostException()
            }
            val reserveId = System.currentTimeMillis()
            val reservationDto = DtoMapper.reservationModelToDto(reservationModel, reserveId)
            reservationRemoteDataSource.createReservation(reservationDto)
            with(reservationModel.reservationHeaderInfo) {
                storeDataSource.updateMerchandiseStock(storeId, cartList.map { it.toDto() })
            }

            val reservationEntity = getMyReservationEntity(reservationModel, reserveId)
            reservationLocalDataSource.insertReservation(reservationEntity)
            Result.success(
                reservationDto.toMyReservationModel(
                    reservationModel.reservationHeaderInfo.storeName,
                    reservationModel.reservationHeaderInfo.storeAddress
                )
            )
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
                        Result.success(-1)
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
            if (!NetworkManager(context).isNetworkConnected()) {
                throw UnknownHostException()
            }
            val myReservationEntityList = reservationLocalDataSource.getMyReservationEntityList()
            val reserveIdList = myReservationEntityList.flatMap { listOf(it.reserveId) }
            val myReservationList =
                reservationRemoteDataSource.getMyReservationList(reserveIdList).map {
                    val reservation =
                        myReservationEntityList.find { entity -> entity.reserveId == it.reserveId }
                    it.toMyReservationModel(reservation!!.storeName, reservation!!.storeAddress)
                }
            Result.success(myReservationList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
