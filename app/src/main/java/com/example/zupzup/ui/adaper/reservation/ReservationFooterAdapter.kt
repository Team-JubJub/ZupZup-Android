package com.example.zupzup.ui.adaper.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemReservationVisitInfoBinding
import com.example.zupzup.domain.models.CustomerModel

class ReservationFooterAdapter(
    private var visitTime: String = "",
    private val customer: CustomerModel? = null
) :
    RecyclerView.Adapter<ReservationFooterAdapter.ReservationFooterViewHolder>(
    ) {

    fun setReservationFooter(newVisitTime: String, customer: CustomerModel?) {
        visitTime = newVisitTime
        notifyDataSetChanged()
        //notifyItemChanged(0)
    }

    class ReservationFooterViewHolder(
        private val binding: ItemReservationVisitInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            visitTime: String,
            customer: CustomerModel?
        ) {
            binding.visitTime = visitTime
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationFooterViewHolder {
        val binding =
            ItemReservationVisitInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ReservationFooterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReservationFooterViewHolder,
        position: Int,
    ) {
        holder.bind(visitTime, customer)
    }

    override fun getItemCount(): Int {
        return 1
    }
}