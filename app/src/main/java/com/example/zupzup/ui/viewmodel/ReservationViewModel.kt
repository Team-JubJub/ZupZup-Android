package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val isAgree = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            _reservationUiState.emit(UiState.Loading)
            combine(
                headerInfo,
                visitTime,
                customer,
                isAgree
            ) { headerInfo, visitTime, customer, isAgree ->

                if (headerInfo != null) {
                    _reservationUiState.emit(
                        UiState.Success(
                            ReservationModel(
                                headerInfo,
                                visitTime,
                                customer,
                                isAgree
                            )
                        )
                    )
                }

                if (headerInfo != null && visitTime > 0 && customer.name.isNotEmpty() && customer.phoneNumber.isNotEmpty()
                    && isAgree
                ) {
                    _isAllInput.emit(true)
                } else {
                    _isAllInput.emit(false)
                }
            }.collect {}
        }
    }

    fun setIsAgree() {
        viewModelScope.launch {
            isAgree.emit(!isAgree.value)
        }
    }

    fun getIsAgree() : Boolean{
        return isAgree.value
    }

    fun setHeaderInfo(
        reservationHeaderModel: ReservationHeaderModel
    ) {
        viewModelScope.launch {
            headerInfo.emit(
                reservationHeaderModel
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

