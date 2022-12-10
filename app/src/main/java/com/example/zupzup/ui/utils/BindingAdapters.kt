package com.example.zupzup.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.TimePicker
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.R
import com.example.zupzup.domain.models.*
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.adaper.myreservation.MyReservationListAdapter
import com.example.zupzup.ui.adaper.storelist.StoreListRecyclerViewAdapter
import com.example.zupzup.ui.bindinghelper.ReservationBindingHelper
import com.example.zupzup.ui.bindinghelper.ReservationProcessBindingHelper
import com.example.zupzup.ui.bindinghelper.StoreDetailBindingHelper


// StoreList
@BindingAdapter("storeListUiState")
fun bindStoreListRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<List<StoreHeaderInfoModel>>?
) {
    val adapter = recyclerView.adapter as StoreListRecyclerViewAdapter
    when (uiState) {
        is UiState.Success -> {
            recyclerView.visibility = View.VISIBLE
            adapter.submitList(uiState.data)
        }
        else -> {
            recyclerView.visibility = View.GONE
        }
    }
}

@BindingAdapter("storeListUiState")
fun bindStoreListUiStateToProgressBar(
    progressBar: ProgressBar,
    uiState: UiState<List<StoreHeaderInfoModel>>?
) {
    when (uiState) {
        is UiState.Loading -> progressBar.visibility = View.VISIBLE
        else -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("storeListUiState")
fun bindStoreListUiStateToConstraintLayout(
    constraintLayout: ConstraintLayout,
    storeListUiState: UiState<List<StoreHeaderInfoModel>>?
) {
    when (storeListUiState) {
        is UiState.Loading -> constraintLayout.visibility = View.GONE
        is UiState.Success -> {
            constraintLayout.visibility = View.GONE
        }
        is UiState.Error -> {
            if (storeListUiState.code == 1000) {
                constraintLayout.visibility = View.VISIBLE
            }
        }
        else -> {}
    }
}

// StoreDetail
@BindingAdapter("storeDetailUiState", "bindingHelper")
fun bindStoreDetailRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<StoreModel>?,
    bindingHelper: StoreDetailBindingHelper,
) {
    when (uiState) {
        is UiState.Success -> {
            bindingHelper.setHeader(uiState.data.toDetailHeaderModel())
            bindingHelper.setBody(uiState.data.merchandiseList)
        }
    }
}

@BindingAdapter("eventList")
fun bindEventListToTextView(textView: TextView, eventList: List<String>?) {
    var event = ""
    eventList?.forEachIndexed { index, s ->
        event += s
        if (index < eventList.size - 1) {
            event += "\n"
        }
    }
    textView.text = event
}

// Reservation

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

@BindingAdapter("phoneNumber")
fun bindPhoneNumberToTextView(
    textView: TextView,
    phoneNumber: String
) {
    textView.text = phoneNumber.toPhoneNumberStringFormat()
}

@BindingAdapter("saleTime")
fun bindSaleTimeToTextView(
    textView: TextView,
    saleTime: Pair<Int, Int>?
) {
    if (saleTime != null) {
        textView.text = "${saleTime.first.toTimeString()} ~ ${saleTime.second.toTimeString()}"
    }
}

@BindingAdapter("startTime", "endTime")
fun bindSaleTimeBottomSheet(
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

@BindingAdapter("progressUiState", "bindingHelper")
fun bindProgressStateToProgressBarConstraint(
    constraintLayout: ConstraintLayout,
    progressUiState: UiState<MyReservationModel>?,
    bindingHelper: ReservationProcessBindingHelper?
) {
    when (progressUiState) {
        is UiState.Success -> bindingHelper?.navigate(progressUiState.data)
        is UiState.Loading -> constraintLayout.visibility = View.VISIBLE
        else -> constraintLayout.visibility = View.GONE
    }
}

@BindingAdapter("progressUiState")
fun bindProgressUiStateToErrorConstraintLayout(
    constraintLayout: ConstraintLayout,
    progressUiState: UiState<MyReservationModel>?
) {
    when (progressUiState) {
        is UiState.Error -> {
            if (progressUiState.code == 1000) {
                constraintLayout.visibility = View.VISIBLE
            }
        }
        else -> constraintLayout.visibility = View.GONE
    }
}


// ReservationComplete
@BindingAdapter("cartList", "inflater")
fun bindViewTypeToTextView(
    linearLayout: LinearLayout,
    cartList: List<CartModel>?,
    inflater: LayoutInflater
) {
    cartList?.forEach {
        val layout = inflater.inflate(R.layout.item_reservation_cart_list, null)
        val tvName: TextView = layout.findViewById(R.id.tv_cart_item_name)
        val tvItemPrice: TextView = layout.findViewById(R.id.tv_cart_item_total_price)
        if (it == cartList.last()) {
            layout.setBackgroundResource(R.drawable.frame_rectangle_bottom_corner_12_gray0)
        }
        tvName.text = it.itemName
        tvItemPrice.text = "${it.salesPrice}원x${it.amount}개 "
        linearLayout.addView(layout)
    }
}

@BindingAdapter("myReservationUiState")
fun bindMyReservationListToRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<List<MyReservationModel>>?
) {
    val adapter = recyclerView.adapter as MyReservationListAdapter
    when (uiState) {
        is UiState.Success -> {
            adapter.submitList(uiState.data)
        }
    }
}

// MyReservation
@BindingAdapter("reserveId")
fun bindReservationDateToTextView(
    textView: TextView,
    reserveId : Long
) {
    textView.text = reserveId.toDateFormat()
}