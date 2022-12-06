package com.example.zupzup.ui.adaper.storedetail

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.R
import com.example.zupzup.databinding.ItemStoreDetailMerchandiseBinding
import com.example.zupzup.domain.models.MerchandiseModel
import com.example.zupzup.ui.bindinghelper.AmountManageHelper

class StoreDetailBodyAdapter(
    private val amountManageHelper: AmountManageHelper,
) :
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

    class StoreDetailBodyViewHolder(
        private val binding: ItemStoreDetailMerchandiseBinding,
        private val amountManageHelper: AmountManageHelper,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: MerchandiseModel,
            position: Int
        ) {
            with(binding) {
                merchandise = item
                idx = position
                helper = amountManageHelper
                tvMerchandisePrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                if (item.stock == 0) {
                    tvMerchandiseName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }

                btnIncreaseAmount.setOnClickListener {
                    val curAmount = tvMerchandiseAmount.text.toString().toInt()
                    if (curAmount < item.stock) {
                        amountManageHelper.increase(bindingAdapterPosition)
                        tvMerchandiseAmount.text = (curAmount.inc()).toString()
                    }
                }

                btnDecreaseAmount.setOnClickListener {
                    val curAmount = tvMerchandiseAmount.text.toString().toInt()
                    if (curAmount > 0) {
                        amountManageHelper.decrease(bindingAdapterPosition)
                        tvMerchandiseAmount.text = (curAmount.dec()).toString()
                    }
                }

                executePendingBindings()

            }
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
        return StoreDetailBodyViewHolder(binding, amountManageHelper)
    }

    override fun onBindViewHolder(
        holder: StoreDetailBodyViewHolder,
        position: Int,
    ) {
        val merchandise = getItem(position)
        holder.bind(merchandise, position)
    }
}