package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.usecase.GetStoreDetailUseCaseImpl
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    private val getStoreDetailUseCase: GetStoreDetailUseCaseImpl
) : ViewModel() {

    private var isInitialState: Boolean = false

    private var _storeDetailUiState = MutableStateFlow<UiState<StoreModel>>(UiState.Loading)
    val storeDetailUiState: StateFlow<UiState<StoreModel>> get() = _storeDetailUiState

    private var _isCartEmpty = MutableStateFlow<Boolean>(true)
    val isCartEmpty: StateFlow<Boolean> get() = _isCartEmpty

    private var amountList = mutableListOf<Int>()

    fun getStoreDetailById(storeId: Long) {
        if (!isInitialState) {
            isInitialState = true
            viewModelScope.launch {
                getStoreDetailUseCase.invoke(storeId).collect {
                    when (it) {
                        is DataResult.Success -> {
                            _storeDetailUiState.emit(UiState.Success(it.data))
                            amountList = MutableList(it.data.merchandiseList.size) { 0 }
                        }
                        is DataResult.Failure -> {
                            _storeDetailUiState.emit(UiState.Error(1))
                        }
                    }
                }
            }
        }
    }

    fun getAmountList(): List<Int> {
        return amountList
    }

    fun increaseAmount(idx: Int) {
        viewModelScope.launch {
            _isCartEmpty.emit(false)
        }
        amountList[idx] += 1
    }

    fun decreaseAmount(idx: Int) {
        amountList[idx] -= 1
        if (amountList.sum() == 0) {
            viewModelScope.launch {
                _isCartEmpty.emit(true)
            }
        }
    }

}