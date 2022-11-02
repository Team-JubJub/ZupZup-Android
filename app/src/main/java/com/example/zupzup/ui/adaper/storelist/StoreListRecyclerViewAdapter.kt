package com.example.zupzup.ui.adaper.storelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemStoreCardBinding
import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.ui.adaper.storelist.StoreListRecyclerViewAdapter.StoreViewHolder

class StoreListRecyclerViewAdapter(
    private val navigateStoreDetail: (Int) -> Unit
) :
    ListAdapter<StoreHeaderModel, StoreViewHolder>(DiffCallBack) {

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

    class StoreViewHolder(
        private val binding: ItemStoreCardBinding,
        private val navigateToStoreDetail: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreHeaderModel) {
            binding.storeHeader = item
            binding.navigateToStoreDetail = navigateToStoreDetail
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding =
            ItemStoreCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding, navigateStoreDetail)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = getItem(position)
        holder.bind(store)
    }
}