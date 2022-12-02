package com.example.zupzup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.usecase.ProcessReservationUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationProcessViewModel @Inject constructor(
    private val processReservationUseCase: ProcessReservationUseCase
) : ViewModel() {

    private var _processUiState = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val processUiState: StateFlow<UiState<Int>> get() = _processUiState

    override fun onCleared() {
        Log.d("TAG", "onCleared: ")
        super.onCleared()
    }
    fun makeReservation(
        reservationHeaderInfo: ReservationHeaderModel,
        visitTime: Int,
        customer: CustomerModel,
        hostPhoneNumber: String,
    ) {
        viewModelScope.launch {
            processReservationUseCase(
                ReservationModel(
                    reservationHeaderInfo,
                    visitTime,
                    customer,
                    true
                ),
                hostPhoneNumber
            ).collect {
                when (it) {
                    is DataResult.Success -> {
                        _processUiState.emit(UiState.Success(0))
                    }
                    is DataResult.Failure -> {
                        _processUiState.emit(UiState.Error(1))
                    }
                }
            }
        }

    }
}