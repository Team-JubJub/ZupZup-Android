package com.example.zupzup.ui.adaper.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemReservationStoreInfoBinding
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.ReservationHeaderModel

class ReservationHeaderAdapter(
    private var reservationHeaderModel: ReservationHeaderModel = ReservationHeaderModel(
        0, "", "",
        listOf()
    )
) :
    RecyclerView.Adapter<ReservationHeaderAdapter.ReservationHeaderViewHolder>() {

    fun setReservationHeader(headerData: ReservationHeaderModel) {
        reservationHeaderModel = headerData
        notifyItemChanged(0)
    }

    class ReservationHeaderViewHolder(
        private val binding: ItemReservationStoreInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ReservationHeaderModel
        ) {
//            val adapter = ReservationCartListAdapter()
//            adapter.submitList(item.cartList)
//            with(binding) {
//                rcvCartList.adapter = adapter
//                rcvCartList.layoutManager = LinearLayoutManager(binding.rcvCartList.context)
//            }
            binding.reservationHeader = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationHeaderViewHolder {
        val binding =
            ItemReservationStoreInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ReservationHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReservationHeaderViewHolder,
        position: Int,
    ) {
        holder.bind(reservationHeaderModel)
    }

    override fun getItemCount(): Int {
        return 1
    }
}