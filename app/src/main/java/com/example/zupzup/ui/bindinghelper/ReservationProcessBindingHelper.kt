package com.example.zupzup.ui.bindinghelper

import com.example.zupzup.domain.models.MyReservationModel

class ReservationProcessBindingHelper(
    private val makeReservation : () -> Unit,
    private val navigateToReservationCompleteFragment: (MyReservationModel) -> Unit
) {
    fun navigate(reservation: MyReservationModel) {
        navigateToReservationCompleteFragment(reservation)
    }

    fun retryMakeReservation() {
        makeReservation()
    }

}