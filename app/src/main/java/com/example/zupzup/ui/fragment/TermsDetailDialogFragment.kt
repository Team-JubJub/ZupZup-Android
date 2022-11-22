package com.example.zupzup.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.zupzup.databinding.FragmentTermsDetailDialogBinding

class TermsDetailDialogFragment : DialogFragment() {

    private var _binding: FragmentTermsDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTermsDetailDialogBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTermsDetail.text =
            "qwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwf\n" + "qwfqwfqwfqwfqwfqqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfqwfwf\n" + "qwfqwfqwfqwfqwfqwf\n" + "qwfqwfqwfqwfqwfqwf\n" + "qwfqwfqwfqwfqwfqwf\n" + "qwfqwfqwfqwfqwfqwf\n" + "qwfqwfqwfqwfqwfqwf\n"
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}