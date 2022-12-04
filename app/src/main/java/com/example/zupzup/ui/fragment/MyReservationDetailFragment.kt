package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.zupzup.databinding.FragmentMyReservationDetailBinding


class MyReservationDetailFragment : Fragment() {

    private var _binding: FragmentMyReservationDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MyReservationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyReservationDetailBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        with(binding) {
            inflater = requireActivity().layoutInflater
            reservation = args.myReservation
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}