package com.example.zupzup.ui.bindinghelper

class ReservationProcessBindingHelper(
    private val navigateToReservationCompleteFragment : () -> Unit
) {
  fun navigate() {
      navigateToReservationCompleteFragment()
  }

}