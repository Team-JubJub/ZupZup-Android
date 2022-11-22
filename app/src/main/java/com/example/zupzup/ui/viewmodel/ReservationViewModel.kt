package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor() : ViewModel() {


    private val _reservationUiState = MutableStateFlow<UiState<ReservationModel>>(UiState.Loading)
    val reservationUiState: StateFlow<UiState<ReservationModel>> get() = _reservationUiState

    private val _isAllInput = MutableStateFlow(false)
    val isAllInput: StateFlow<Boolean> get() = _isAllInput

    private val headerInfo = MutableStateFlow<ReservationHeaderModel?>(null)

    private val visitTime = MutableStateFlow(0)

    private val customer = MutableStateFlow(CustomerModel("", ""))

    private val isApprove = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            _reservationUiState.emit(UiState.Loading)
            combine(
                headerInfo,
                visitTime,
                customer,
                isApprove
            ) { headerInfo, visitTime, customer, isApprove ->

                if (headerInfo != null) {
                    _reservationUiState.emit(
                        UiState.Success(
                            ReservationModel(
                                headerInfo,
                                visitTime,
                                customer
                            )
                        )
                    )
                }

                if (headerInfo != null && visitTime > 0 && customer.name.isNotEmpty() && customer.phoneNumber.isNotEmpty()
                    && isApprove
                ) {
                    _isAllInput.emit(true)
                } else {
                    _isAllInput.emit(false)
                }
            }.collect {}
        }
    }

    fun setIsApprove()  {
        viewModelScope.launch {
            if(isApprove.value) {
                isApprove.emit(false)
            }
            else{
                isApprove.emit(true)
            }
        }
    }

    fun getIsApprove(): Boolean {
        return isApprove.value
    }

    fun setHeaderInfo(
        storeId: Long,
        storeName: String,
        storeAddress: String,
        cartList: List<CartModel>,
        start: Int,
        end: Int
    ) {
        viewModelScope.launch {
            headerInfo.emit(
                ReservationHeaderModel(
                    storeId,
                    storeName,
                    storeAddress,
                    cartList,
                    Pair(start, end)
                )
            )
        }
    }

    fun setVisitTime(newVisitTime: Int) {
        viewModelScope.launch {
            visitTime.emit(newVisitTime)
        }
    }

    fun getSelectedVisitTime(): Int {
        return visitTime.value
    }

    fun setCustomerInfo(newCustomer: CustomerModel) {
        viewModelScope.launch {
            customer.emit(newCustomer)
        }
    }

    fun getCustomerInfo(): CustomerModel {
        return customer.value
    }


}

