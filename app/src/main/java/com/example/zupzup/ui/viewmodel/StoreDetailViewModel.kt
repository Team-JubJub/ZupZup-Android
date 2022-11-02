package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.usecase.GetStoreDetailUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    private val getStoreDetailUseCase: GetStoreDetailUseCase
) : ViewModel() {

    private var _storeDetailUiState = MutableStateFlow<UiState<StoreModel>>(UiState.Loading)
    val storeDetailUiState: StateFlow<UiState<StoreModel>> get() = _storeDetailUiState


    fun getStoreDetailById(storeId: Int) {
        viewModelScope.launch {
            _storeDetailUiState.emit(UiState.Loading)
            getStoreDetailUseCase.invoke(storeId).apply {
                if (this is DataResult.Success) {
                    _storeDetailUiState.emit(UiState.Success(data))
                } else if (this is DataResult.Failure) {
                    _storeDetailUiState.emit(UiState.Error(1))
                }
            }

        }
    }
}