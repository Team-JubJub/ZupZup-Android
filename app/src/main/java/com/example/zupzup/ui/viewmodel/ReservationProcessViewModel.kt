package com.example.zupzup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.usecase.MakeReservationOnFireBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationProcessViewModel @Inject constructor(
    private val makeReservationOnFireBaseUseCase: MakeReservationOnFireBaseUseCase
) : ViewModel() {

    fun makeReservation(
        reservationHeaderInfo: ReservationHeaderModel,
        visitTime: Int,
        customer: CustomerModel
    ) {
        viewModelScope.launch {
            makeReservationOnFireBaseUseCase(
                ReservationModel(
                    reservationHeaderInfo,
                    visitTime,
                    customer
                )
            ).collect()
        }

    }
}