package com.example.zupzup.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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


// Common
@BindingAdapter("uiState")
fun bindStoreListUiStateToProgressBar(
    progressBar: ProgressBar,
    uiState: UiState<Any>?
) {
    when (uiState) {
        is UiState.Loading -> progressBar.visibility = View.VISIBLE
        else -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("uiState")
fun bindStoreListUiStateToConstraintLayout(
    constraintLayout: ConstraintLayout,
    uiState: UiState<Any>?
) {
    when (uiState) {
        is UiState.Error -> {
            if (uiState.code == 1000) {
                constraintLayout.visibility = View.VISIBLE
            }
        }
        else -> {
            constraintLayout.visibility = View.GONE
        }
    }
}

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
        tvName.text = "${it.itemName} ${it.amount}개"
        tvItemPrice.text = "${it.salesPrice * it.amount}원 "
        linearLayout.addView(layout)
    }
}

// MyReservation

@BindingAdapter("myReservationUiState")
fun bindMyReservationListToRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<List<MyReservationModel>>?
) {
    val adapter = recyclerView.adapter as MyReservationListAdapter
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

@BindingAdapter("reserveId")
fun bindReservationDateToTextView(
    textView: TextView,
    reserveId: Long
) {
    textView.text = reserveId.toDateFormat()
}

@BindingAdapter("reserveState")
fun bindReserveStateToTextView(
    textView: TextView,
    reserveState: String
) {
    var text = ""
    var textColor = 0
    var backgroundColor = 0
    when (reserveState) {
        "NEW" -> {
            text = "대기"
            backgroundColor = R.color.main
            textColor = R.color.text
        }
        "CANCEL" -> {
            text = "취소"
            backgroundColor = R.color.warning1
            textColor = R.color.white
        }
        "CONFIRM" -> {
            text = "확정"
            backgroundColor = R.color.green
            textColor = R.color.text
        }
        "COMPLETE" -> {
            text = "완료"
            backgroundColor = R.color.text_gray
            textColor = R.color.cool_gray1
        }
    }
    textView.text = text
    textView.setTextColor(ContextCompat.getColor(textView.context, textColor))
    DrawableCompat.setTint(
        textView.background,
        ContextCompat.getColor(textView.context, backgroundColor)
    )
}