package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.models.ReservationModel
import com.example.zupzup.domain.usecase.ProcessReservationUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationProcessViewModel @Inject constructor(
    private val processReservationUseCase: ProcessReservationUseCase
) : ViewModel() {

    private var _processUiState = MutableStateFlow<UiState<MyReservationModel>>(UiState.Loading)
    val processUiState = _processUiState.asStateFlow()

    fun makeReservation(
        reservationModel: ReservationModel,
        hostPhoneNumber: String,
    ) {
        viewModelScope.launch {
            processReservationUseCase(reservationModel, hostPhoneNumber).collect {
                when (it) {
                    is DataResult.Success -> {
                        _processUiState.emit(UiState.Success(it.data))
                    }
                    is DataResult.Failure -> {
                        _processUiState.emit(UiState.Error(1))
                    }
                }
            }
        }

    }
}