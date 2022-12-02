package com.example.zupzup.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.zupzup.R
import com.example.zupzup.databinding.FragmentCustomerInfoSetBottomSheetBinding
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.ui.utils.toPhoneNumberIntFormat
import com.example.zupzup.ui.utils.toPhoneNumberStringFormat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class CustomerInfoSetBottomSheet(
    private val customer: CustomerModel?,
    private val setCustomerInfoToViewModel: (CustomerModel) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCustomerInfoSetBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentCustomerInfoSetBottomSheetBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPhoneNumberFormatToEditText()
        initBinding()
    }

    private fun initBinding() {
        with(binding) {
            if (customer != null) {
                name = customer.name
                phoneNumber = customer.phoneNumber.toPhoneNumberStringFormat()
            } else {
                name = ""
                phoneNumber = ""
            }
            setCustomerInfoBtnClick = ::setCustomerInfo
        }
    }

    private fun setCustomerInfo(name: String, phoneNumber: String) {
        if (name.isNotEmpty() && phoneNumber.length == 13) {
            setCustomerInfoToViewModel(CustomerModel(name, phoneNumber.toPhoneNumberIntFormat()))
            dismiss()
        } else {
            Toast.makeText(requireContext(), "올바르지 않은 입력입니다. 다시 확인해주세요", LENGTH_SHORT).show()
        }
    }

    private fun initPhoneNumberFormatToEditText() {
        binding.etvCustomerPhoneNumber.addTextChangedListener(
            PhoneNumberFormattingTextWatcher(
                Locale.KOREA.country
            )
        )
    }
}

