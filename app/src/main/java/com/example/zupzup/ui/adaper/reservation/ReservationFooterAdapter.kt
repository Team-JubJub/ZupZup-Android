package com.example.zupzup.ui.adaper.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemReservationVisitInfoBinding
import com.example.zupzup.domain.models.CustomerModel

class ReservationFooterAdapter(
    private var visitTime: Int,
    private var customer: CustomerModel,
    private val showSetVisitTimeBottomSheet: () -> Unit,
    private val showSetCustomerInfoBottomSheet: () -> Unit
) :
    RecyclerView.Adapter<ReservationFooterAdapter.ReservationFooterViewHolder>(
    ) {

    fun setReservationFooter(newVisitTime: Int, newCustomer: CustomerModel) {
        visitTime = newVisitTime
        customer = newCustomer
        notifyItemChanged(0)
    }

    class ReservationFooterViewHolder(
        private val binding: ItemReservationVisitInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            newVisitTime: Int,
            newCustomer: CustomerModel,
            showSetVisitTimeBottomSheet: () -> Unit,
            showSetCustomerInfoBottomSheet: () -> Unit
        ) {
            with(binding) {
                visitTime = newVisitTime
                customer = newCustomer
                showSetVisitTimeBottomSheetBtnOnClick = showSetVisitTimeBottomSheet
                showSetCustomerInfoBottomSheetBtnOnClick = showSetCustomerInfoBottomSheet
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
        holder.bind(
            visitTime,
            customer,
            showSetVisitTimeBottomSheet,
            showSetCustomerInfoBottomSheet
        )
    }

    override fun getItemCount(): Int {
        return 1
    }
}