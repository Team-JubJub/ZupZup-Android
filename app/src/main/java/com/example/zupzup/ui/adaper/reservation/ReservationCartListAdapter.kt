package com.example.zupzup.ui.adaper.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.R
import com.example.zupzup.databinding.ItemReservationCartListBinding
import com.example.zupzup.domain.models.CartModel

class ReservationCartListAdapter :
    ListAdapter<CartModel, ReservationCartListAdapter.ReservationCartListViewHolder>(
        DiffCallBack
    ) {

    fun setReservationCartList(newCartList: List<CartModel>) {
        submitList(newCartList)
    }

    object DiffCallBack : DiffUtil.ItemCallback<CartModel>() {
        override fun areItemsTheSame(
            oldItem: CartModel,
            newItem: CartModel
        ): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(
            oldItem: CartModel,
            newItem: CartModel
        ): Boolean {
            return oldItem.itemId == newItem.itemId
        }
    }

    inner class ReservationCartListViewHolder(
        private val binding: ItemReservationCartListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: CartModel,
        ) {
            if (absoluteAdapterPosition == currentList.size) {
                binding.constraintReservationCartItem.setBackgroundResource(R.drawable.frame_rectangle_bottom_corner_12_gray0)
            }
            binding.cartModel = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCartListViewHolder {
        val binding =
            ItemReservationCartListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ReservationCartListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReservationCartListViewHolder,
        position: Int,
    ) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

}