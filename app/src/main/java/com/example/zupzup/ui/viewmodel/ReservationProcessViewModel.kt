package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
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

    fun makeReservation(
        reservationModel: ReservationModel,
        hostPhoneNumber: String,
    ) {
        viewModelScope.launch {
            processReservationUseCase(reservationModel, hostPhoneNumber).collect {
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