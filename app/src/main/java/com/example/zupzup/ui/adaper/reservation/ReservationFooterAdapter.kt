package com.example.zupzup.ui.adaper.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemReservationVisitInfoBinding
import com.example.zupzup.domain.models.CustomerModel

class ReservationFooterAdapter(
    private var visitTime: Int = 0,
    private val customer: CustomerModel? = null,
    private val showSetVisitTimeBottomSheet: () -> Unit
) :
    RecyclerView.Adapter<ReservationFooterAdapter.ReservationFooterViewHolder>(
    ) {

    fun setReservationFooter(newVisitTime: Int, customer: CustomerModel?) {
        visitTime = newVisitTime
        notifyItemChanged(0)
    }

    class ReservationFooterViewHolder(
        private val binding: ItemReservationVisitInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            newVisitTime: Int,
            customer: CustomerModel?,
            showSetVisitTimeBottomSheet : () -> Unit
        ) {
            with(binding) {
                visitTime = newVisitTime
                showSetVisitTimeBottomSheetBtnOnClick = showSetVisitTimeBottomSheet
            }
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
        holder.bind(visitTime, customer, showSetVisitTimeBottomSheet)
    }

    override fun getItemCount(): Int {
        return 1
    }
}