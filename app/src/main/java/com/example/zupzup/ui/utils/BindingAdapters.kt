package com.example.zupzup.ui.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.adaper.storedetail.StoreDetailBodyAdapter
import com.example.zupzup.ui.adaper.storedetail.StoreDetailHeaderAdapter
import com.example.zupzup.ui.adaper.storelist.StoreListRecyclerViewAdapter

@BindingAdapter("storeListUiState")
fun bindStoreListRecyclerView(
    recyclerView: RecyclerView,
    uiState: UiState<List<StoreHeaderInfoModel>>?
) {
    val adapter = recyclerView.adapter as StoreListRecyclerViewAdapter
    when (uiState) {
        is UiState.Success -> {
            adapter.submitList(uiState.data)
        }
    }
}

@BindingAdapter("storeDetailUiState")
fun bindStoreDetailRecyclerView(recyclerView: RecyclerView, uiState: UiState<StoreModel>?) {
    val headerAdapter = StoreDetailHeaderAdapter()
    val bodyAdapter = StoreDetailBodyAdapter()
    when (uiState) {
        is UiState.Success -> {
            headerAdapter.submitList(listOf(uiState.data.toDetailHeaderModel()))
            bodyAdapter.submitList(uiState.data.merchandiseList)
            val adapter = ConcatAdapter(headerAdapter, bodyAdapter)
            recyclerView.adapter = adapter
        }
    }
}

@BindingAdapter("eventList")
fun bindEventListToTextView(textView: TextView, eventList : List<String>) {
    var event = ""
    eventList.forEachIndexed { index, s ->
        event += s
        if(index < eventList.size - 1) {
            event += "\n"
        }
    }
    textView.text = event
}