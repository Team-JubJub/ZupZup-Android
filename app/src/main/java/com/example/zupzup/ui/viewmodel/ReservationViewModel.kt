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

    private val headerInfo = MutableStateFlow<ReservationHeaderModel?>(null)

    private val visitTime = MutableStateFlow("")

    private val customer = MutableStateFlow<CustomerModel?>(null)

    init {
        viewModelScope.launch {
            _reservationUiState.emit(UiState.Loading)
            combine(headerInfo, visitTime, customer) { headerInfo, visitTime, customer ->

                if(headerInfo != null) {
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

            }.collect{}
        }
    }

    fun a() {
        viewModelScope.launch {
            visitTime.emit("123")
        }
    }

    fun setHeaderInfo(
        storeId: Long,
        storeName: String,
        storeAddress: String,
        cartList: List<CartModel>
    ) {
        viewModelScope.launch {
            headerInfo.emit(ReservationHeaderModel(storeId, storeName, storeAddress, cartList))
        }
    }

}

