package com.example.zupzup.ui.adaper.reservation

import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel

class ReservationBindingHelper(
    private val setHeaderData: (ReservationHeaderModel) -> Unit,
    private val setFooterData: (Int, CustomerModel?) -> Unit,
    private val setCartListData : (List<CartModel>) -> Unit
) {
    fun setHeader(headerInfo: ReservationHeaderModel) {
        setHeaderData(headerInfo)
    }

    fun setFooter(visitTime: Int, customer: CustomerModel?) {
        setFooterData(visitTime, customer)
    }

    fun setCartList(cartList : List<CartModel>) {
        setCartListData(cartList)
    }
}