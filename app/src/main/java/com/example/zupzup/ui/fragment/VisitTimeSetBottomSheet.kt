package com.example.zupzup.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zupzup.R
import com.example.zupzup.databinding.FragmentVisitTimeSetBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class VisitTimeSetBottomSheet(
    private val saleTime: Pair<Int, Int>,
    private val selectedTime: Int,
    private val setVisitTime: (Int) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: FragmentVisitTimeSetBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitTimeSetBottomSheetBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun setVisitTimeBtnClickListener() {
        with(binding.timepicker) {
            val hour = hour
            val minutes = minute
            setVisitTime(hour * 100 + minutes)
            dismiss()
        }
    }

    private fun initBinding() {
        with(binding) {
            startTime = saleTime.first
            endTime = saleTime.second
            time = selectedTime
            setVisitTimeBtnClick = { setVisitTimeBtnClickListener() }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}