package com.example.zupzup.data.datasource.reservation

import com.example.zupzup.data.dto.reservation.ReservationDto
import com.example.zupzup.di.FireBaseModule
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationRemoteDataSourceImpl @Inject constructor(
    @FireBaseModule.ReservationRef private val reservationRef: CollectionReference,
) : ReservationRemoteDataSource {

    override suspend fun createReservation(reservation: ReservationDto) {
        reservationRef.document(reservation.reserveId.toString()).set(reservation).await()
    }
}