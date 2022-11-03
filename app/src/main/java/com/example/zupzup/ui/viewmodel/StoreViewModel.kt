package com.example.zupzup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.usecase.GetStoreListUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getStoreListUseCase: GetStoreListUseCase
) : ViewModel() {

    private var _storeUiState = MutableStateFlow<UiState<List<StoreModel>>>(UiState.Loading)
    val storeUiState: StateFlow<UiState<List<StoreModel>>> get() = _storeUiState

    fun getStoreList() {
        viewModelScope.launch {
            _storeUiState.emit(UiState.Loading)
            Log.d("TAG", "getStoreList: ")
            getStoreListUseCase.invoke().apply {
                if(this is DataResult.Success) {
                    _storeUiState.emit(UiState.Success(data))
                    Log.d("TAG", "getStoreList: 123")
                }
                else if(this is DataResult.Failure) {
                    _storeUiState.emit(UiState.Error(1))
                    Log.d("TAG", "getStoreList: 12")
                }
            }
        }
    }
}