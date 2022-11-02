package com.example.zupzup.ui.adaper.storedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemStoreDetailStoreInfoBinding
import com.example.zupzup.domain.models.StoreHeaderModel

class StoreDetailHeaderAdapter :
    ListAdapter<StoreHeaderModel, StoreDetailHeaderAdapter.StoreDetailHeaderViewHolder>(DiffCallBack) {

    object DiffCallBack : DiffUtil.ItemCallback<StoreHeaderModel>() {
        override fun areItemsTheSame(
            oldItem: StoreHeaderModel,
            newItem: StoreHeaderModel
        ): Boolean {
            return oldItem.storeId == newItem.storeId
        }

        override fun areContentsTheSame(
            oldItem: StoreHeaderModel,
            newItem: StoreHeaderModel
        ): Boolean {
            return oldItem.storeId == newItem.storeId
        }
    }

    class StoreDetailHeaderViewHolder(private val binding: ItemStoreDetailStoreInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreHeaderModel) {
            binding.storeHeader = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreDetailHeaderViewHolder {
        val binding =
            ItemStoreDetailStoreInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StoreDetailHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StoreDetailHeaderViewHolder,
        position: Int
    ) {
        val store = getItem(position)
        holder.bind(store)
    }
}