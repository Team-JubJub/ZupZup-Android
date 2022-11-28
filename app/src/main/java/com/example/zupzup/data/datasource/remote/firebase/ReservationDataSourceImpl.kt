package com.example.zupzup.data.datasource.remote.firebase

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.zupzup.data.dto.Reservation
import com.example.zupzup.di.FireBaseModule
import com.google.firebase.firestore.CollectionReference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.net.UnknownHostException
import javax.inject.Inject

class ReservationDataSourceImpl @Inject constructor(
    @FireBaseModule.ReservationRef private val reservationRef: CollectionReference,
    @ApplicationContext private val context: Context
) : ReservationDataSource {
    override suspend fun createReservation(reservation: Reservation): Result<Int> {
        return try {
            val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
            val currentNetwork = connectivityManager?.activeNetwork
            if (currentNetwork != null) {
                Log.d("TAG", "createReservation: internet")
                reservationRef.document(reservation.reserveId.toString()).set(reservation).await()
                Result.success(0)
            } else {
                throw UnknownHostException()
            }
        } catch (e: Exception) {
            Log.d("TAG", "createReservation: exception")
            Result.failure(e)
        }
    }
}