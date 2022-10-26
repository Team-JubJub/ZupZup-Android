package com.example.zupzup.ui.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.adaper.StoreListRecyclerViewAdapter

@BindingAdapter("uiState")
fun bindRecyclerView(recyclerView: RecyclerView, uiState: UiState<List<StoreModel>>?) {
    val adapter = recyclerView.adapter as StoreListRecyclerViewAdapter
    when (uiState) {
        is UiState.Success -> {
            adapter.submitList(uiState.data)
        }
    }
}