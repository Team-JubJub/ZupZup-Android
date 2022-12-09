package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.domain.usecase.GetMyReservationListUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReservationViewModel @Inject constructor(
    private val getMyReservationListUseCase: GetMyReservationListUseCase
) : ViewModel() {

    private var _myReservationUiState =
        MutableStateFlow<UiState<List<MyReservationModel>>>(UiState.Loading)
    val myReservationUiState = _myReservationUiState.asStateFlow()

    init {
        getMyReservationList()
    }

    private fun getMyReservationList() {
        viewModelScope.launch {
            getMyReservationListUseCase.invoke().collect {
                when (it) {
                    is DataResult.Success -> {
                        _myReservationUiState.emit(UiState.Success(it.data))
                    }
                    is DataResult.Failure -> {
                        _myReservationUiState.emit(UiState.Error(1))
                    }
                }
            }
        }
    }
}