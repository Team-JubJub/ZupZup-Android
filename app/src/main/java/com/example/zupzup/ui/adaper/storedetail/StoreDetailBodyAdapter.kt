package com.example.zupzup.ui.adaper.storedetail

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemStoreDetailMerchandiseBinding
import com.example.zupzup.domain.models.MerchandiseModel

class StoreDetailBodyAdapter :
    ListAdapter<MerchandiseModel, StoreDetailBodyAdapter.StoreDetailBodyViewHolder>(
        DiffCallBack
    ) {

    object DiffCallBack : DiffUtil.ItemCallback<MerchandiseModel>() {
        override fun areItemsTheSame(
            oldItem: MerchandiseModel,
            newItem: MerchandiseModel
        ): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(
            oldItem: MerchandiseModel,
            newItem: MerchandiseModel
        ): Boolean {
            return oldItem.itemId == newItem.itemId
        }
    }

    class StoreDetailBodyViewHolder(private val binding: ItemStoreDetailMerchandiseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MerchandiseModel) {
            binding.merchandise = item
            binding.tvMerchandisePrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreDetailBodyViewHolder {
        val binding =
            ItemStoreDetailMerchandiseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StoreDetailBodyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StoreDetailBodyViewHolder,
        position: Int
    ) {
        val merchandise = getItem(position)
        holder.bind(merchandise)
    }
}