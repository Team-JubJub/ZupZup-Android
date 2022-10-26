package com.example.zupzup.ui.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemStoreCardBinding
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.ui.adaper.StoreListRecyclerViewAdapter.StoreViewHolder

class StoreListRecyclerViewAdapter :
    ListAdapter<StoreModel, StoreViewHolder>(DiffCallBack) {

    object DiffCallBack  : DiffUtil.ItemCallback<StoreModel>() {
        override fun areItemsTheSame(oldItem: StoreModel, newItem: StoreModel): Boolean {
            return oldItem.storeId == newItem.storeId
        }

        override fun areContentsTheSame(oldItem: StoreModel, newItem: StoreModel): Boolean {
            return oldItem.storeId == newItem.storeId
        }
    }

    class StoreViewHolder(private val binding : ItemStoreCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : StoreModel) {
            binding.store = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = getItem(position)
        holder.bind(store)
    }
}