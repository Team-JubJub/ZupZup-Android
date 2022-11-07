package com.example.zupzup.ui.adaper.storedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemStoreDetailStoreInfoBinding
import com.example.zupzup.domain.models.StoreDetailHeaderModel
import com.example.zupzup.domain.models.StoreHeaderInfoModel

class StoreDetailHeaderAdapter :
    ListAdapter<StoreDetailHeaderModel, StoreDetailHeaderAdapter.StoreDetailHeaderViewHolder>(
        DiffCallBack
    ) {

    object DiffCallBack : DiffUtil.ItemCallback<StoreDetailHeaderModel>() {
        override fun areItemsTheSame(
            oldItem: StoreDetailHeaderModel,
            newItem: StoreDetailHeaderModel
        ): Boolean {
            return oldItem.headerInfo.storeID == newItem.headerInfo.storeID
        }

        override fun areContentsTheSame(
            oldItem: StoreDetailHeaderModel,
            newItem: StoreDetailHeaderModel
        ): Boolean {
            return oldItem.headerInfo.storeID == newItem.headerInfo.storeID
        }
    }

    class StoreDetailHeaderViewHolder(private val binding: ItemStoreDetailStoreInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreDetailHeaderModel) {
            binding.storeDetailHeader = item
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