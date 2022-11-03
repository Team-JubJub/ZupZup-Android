package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.domain.usecase.GetStoreListUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getStoreListUseCase: GetStoreListUseCase
) : ViewModel() {

    private var _storeUiState = MutableStateFlow<UiState<List<StoreHeaderModel>>>(UiState.Loading)
    val storeUiState: StateFlow<UiState<List<StoreHeaderModel>>> get() = _storeUiState

    fun getStoreList() {
        viewModelScope.launch {
            _storeUiState.emit(UiState.Loading)
            getStoreListUseCase.invoke().apply {
                if (this is DataResult.Success) {
                    _storeUiState.emit(UiState.Success(data))
                } else if (this is DataResult.Failure) {
                    _storeUiState.emit(UiState.Error(1))
                }
            }
        }
    }
}