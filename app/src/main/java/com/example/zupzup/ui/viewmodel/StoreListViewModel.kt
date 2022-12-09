package com.example.zupzup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.usecase.GetStoreListUseCase
import com.example.zupzup.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getStoreListUseCase: GetStoreListUseCase
) : ViewModel() {

    private var _storeUiState =
        MutableStateFlow<UiState<List<StoreHeaderInfoModel>>>(UiState.Loading)
    val storeUiState = _storeUiState.asStateFlow()

    init {
        getStoreList()
    }

    private fun getStoreList() {
        viewModelScope.launch {
            getStoreListUseCase.invoke().collect {
                when (it) {
                    is DataResult.Success -> {
                        _storeUiState.emit(UiState.Success(it.data))
                    }
                    is DataResult.Failure -> {
                        _storeUiState.emit(UiState.Error(1))
                    }
                }
            }

        }
    }
}