package com.example.zupzup.ui.fragment.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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
    ): View {
        _binding = FragmentVisitTimeSetBottomSheetBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initNumberPicker()
    }

    private fun getTimeInterval(time: Int): Int {
        var hour = 0
        if (time > 100) { // 1000 이상
            hour = time / 100
        }
        return hour
    }

    private fun cancelBtnClickListener() {
        dismiss()
    }

    private fun initNumberPicker() {
        with(binding) {
            numberPickerHour.minValue = getTimeInterval(saleTime.first)
            numberPickerHour.maxValue = getTimeInterval(saleTime.second)
            numberPickerMinute.minValue = 0
            numberPickerMinute.maxValue = 59
            numberPickerAtNoon.minValue = 0
            numberPickerAtNoon.maxValue = 1
            numberPickerAtNoon.displayedValues = arrayOf("AM", "PM")
        }
    }

    private fun setVisitTimeBtnClickListener() {
        with(binding) {
            val hour = numberPickerHour.value
            val minutes = numberPickerMinute.value
            if (numberPickerAtNoon.value == 0 && hour > 12) {
                Toast.makeText(requireContext(), "AM, PM 을 확인해주세요 !", LENGTH_SHORT).show()
            } else if (hour * 100 + minutes > saleTime.second) {
                Toast.makeText(requireContext(), "마감시간을 넘었습니다. !", LENGTH_SHORT).show()
            } else {
                setVisitTime(hour * 100 + minutes)
                dismiss()
            }
        }
    }

    private fun initBinding() {
        with(binding) {
            startTime = saleTime.first
            endTime = saleTime.second
            cancelBtnClick = ::cancelBtnClickListener
            if (selectedTime == 0) {
                hour = saleTime.first / 100
                minute = 0
                atNoon = if(saleTime.first > 1200) {
                    1
                } else {
                    0
                }
            } else {
                hour = selectedTime / 100
                minute = selectedTime % 100
                atNoon = if (selectedTime / 100 >= 12) {
                    1
                } else {
                    0
                }
            }
            setVisitTimeBtnClick = { setVisitTimeBtnClickListener() }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}