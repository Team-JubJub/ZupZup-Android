package com.example.zupzup.ui.adaper.reservation

import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel

class ReservationBindingHelper(
    private val navigateToReservationProcess : () -> Unit,
    private val setHeaderData: (ReservationHeaderModel) -> Unit,
    private val setFooterData: (Int, CustomerModel, Boolean) -> Unit,
    private val setCartListData : (List<CartModel>) -> Unit
) {
    fun setHeader(headerInfo: ReservationHeaderModel) {
        setHeaderData(headerInfo)
    }

    fun setFooter(visitTime: Int, customer: CustomerModel, isAgree : Boolean) {
        setFooterData(visitTime, customer, isAgree)
    }

    fun setCartList(cartList : List<CartModel>) {
        setCartListData(cartList)
    }

    fun navigateToReservationProcessFragment() {
        navigateToReservationProcess()
    }
}