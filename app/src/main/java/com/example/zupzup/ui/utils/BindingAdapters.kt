package com.example.zupzup.ui.utils

import android.widget.CheckBox
import android.widget.TextView
import android.widget.TimePicker
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.adaper.reservation.ReservationBindingHelper
import com.example.zupzup.ui.adaper.storedetail.StoreDetailBodyAdapter
import com.example.zupzup.ui.adaper.storedetail.StoreDetailHeaderAdapter
import com.example.zupzup.ui.adaper.storelist.StoreListRecyclerViewAdapter
import com.example.zupzup.ui.custom.CustomClearEditText

@BindingAdapter("storeListUiState")
fun bindStoreListRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<List<StoreHeaderInfoModel>>?
) {
    val adapter = recyclerView.adapter as StoreListRecyclerViewAdapter
    when (uiState) {
        is UiState.Success -> {
            adapter.submitList(uiState.data)
        }
    }
}

@BindingAdapter("storeDetailUiState", "amountManageHelper")
fun bindStoreDetailRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<StoreModel>?,
    amountManageHelper: AmountManageHelper
) {
    val headerAdapter = StoreDetailHeaderAdapter()
    val bodyAdapter = StoreDetailBodyAdapter(amountManageHelper)
    when (uiState) {
        is UiState.Success -> {
            headerAdapter.submitList(listOf(uiState.data.toDetailHeaderModel()))
            bodyAdapter.submitList(uiState.data.merchandiseList)
            val adapter = ConcatAdapter(headerAdapter, bodyAdapter)
            recyclerView.adapter = adapter
        }
    }
}

@BindingAdapter("eventList")
fun bindEventListToTextView(textView: TextView, eventList: List<String>) {
    var event = ""
    eventList.forEachIndexed { index, s ->
        event += s
        if (index < eventList.size - 1) {
            event += "\n"
        }
    }
    textView.text = event
}

@BindingAdapter("totalAmount")
fun bindTotalAmountToTextView(textView: TextView, cartList: List<CartModel>) {
    textView.text = cartList.sumOf { it.amount }.toString() + "개"
}

@BindingAdapter("totalPrice")
fun bindTotalPriceToTextView(textView: TextView, cartList: List<CartModel>) {
    textView.text = cartList.sumOf { it.amount * it.salesPrice }.toString() + "원"
}

@BindingAdapter("reservationHeaderUiState", "bindingHelper")
fun bindReservationRecyclerView(
    recyclerView: RecyclerView,
    headerInfoState: UiState<ReservationModel>?,
    bindingHelper: ReservationBindingHelper
) {
    when (headerInfoState) {
        is UiState.Success -> {
            with(headerInfoState.data) {
                bindingHelper.setHeader(reservationHeaderInfo)
                bindingHelper.setFooter(visitTime, customer, isAgree)
                bindingHelper.setCartList(reservationHeaderInfo.cartList)
            }
        }
        else -> {}
    }
}

@BindingAdapter("visitTime")
fun bindVisitTimeToTextView(
    textView: TextView,
    visitTime: Int
) {
    textView.text = visitTime.toTimeString()
}

@BindingAdapter("startTime", "endTime")
fun bindSaleTimeToTextView(
    textView: TextView,
    startTime: Int,
    endTime: Int
) {
    textView.text = "할인시간 : ${startTime.toTimeString()} ~ ${endTime.toTimeString()}"
}

@BindingAdapter("selectedTime")
fun bindSelectedTimeToTimePicker(
    timePicker: TimePicker,
    selectedTime: Int,
) {
    if (selectedTime != 0) {
        timePicker.hour = selectedTime / 100
        timePicker.minute = selectedTime % 100
    }
}