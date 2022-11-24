package com.example.zupzup.ui.adaper.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemReservationVisitInfoBinding
import com.example.zupzup.domain.models.CustomerModel

class ReservationFooterAdapter(
    private var visitTime: Int,
    private var customer: CustomerModel,
    private var isAgree : Boolean,
    private var setIsApproveToViewModel : () -> Unit,
    private val showSetVisitTimeBottomSheet: () -> Unit,
    private val showSetCustomerInfoBottomSheet: () -> Unit,
    private val showTermsDetailDialog: () -> Unit
) :
    RecyclerView.Adapter<ReservationFooterAdapter.ReservationFooterViewHolder>(
    ) {

    fun setReservationFooter(newVisitTime: Int, newCustomer: CustomerModel, newIsAgree : Boolean) {
        visitTime = newVisitTime
        customer = newCustomer
        isAgree = newIsAgree
        notifyItemChanged(0)
    }

    class ReservationFooterViewHolder(
        private val binding: ItemReservationVisitInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            newVisitTime: Int,
            newCustomer: CustomerModel,
            newIsAgree: Boolean,
            setIsApproveToViewModel: () -> Unit,
            showSetVisitTimeBottomSheet: () -> Unit,
            showSetCustomerInfoBottomSheet: () -> Unit,
            showTermsDetailDialog: () -> Unit
        ) {
            with(binding) {
                visitTime = newVisitTime
                customer = newCustomer
                isAgree = newIsAgree
                approveCheckboxOnClick = setIsApproveToViewModel
                showTermsDetailTextViewOnClick = showTermsDetailDialog
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
            isAgree,
            setIsApproveToViewModel,
            showSetVisitTimeBottomSheet,
            showSetCustomerInfoBottomSheet,
            showTermsDetailDialog
        )
    }

    override fun getItemCount(): Int {
        return 1
    }
}