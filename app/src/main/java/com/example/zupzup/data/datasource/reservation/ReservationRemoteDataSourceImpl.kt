package com.example.zupzup.data.datasource.reservation

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.zupzup.data.dto.Cart
import com.example.zupzup.data.dto.Reservation
import com.example.zupzup.di.FireBaseModule
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.net.UnknownHostException
import javax.inject.Inject

class ReservationRemoteDataSourceImpl @Inject constructor(
    @FireBaseModule.ReservationRef private val reservationRef: CollectionReference,
    @ApplicationContext private val context: Context
) : ReservationDataSource {
    override suspend fun createReservation(reservation: Reservation): Result<Long> {
        return runCatching {
            val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
            val currentNetwork = connectivityManager?.activeNetwork
            if (currentNetwork != null) {
                val reservationDto = reservation as Reservation.ReservationDto
                reservationRef.document(reservation.reserveId.toString()).set(reservationDto).await()
                0
            } else {
                throw UnknownHostException()
            }
        }
    }
}