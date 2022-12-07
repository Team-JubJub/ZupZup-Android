package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.zupzup.databinding.FragmentReservationCompleteBinding

class ReservationCompleteFragment : Fragment() {

    private var _binding: FragmentReservationCompleteBinding? = null
    private val binding get() = _binding!!

    private val args: ReservationCompleteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReservationCompleteBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun navigateHome() {
        findNavController().popBackStack()
    }

    private fun initBinding() {
        with(args) {
            binding.myReservation = myReservation
            binding.inflater = requireActivity().layoutInflater
            binding.navigateHome = ::navigateHome
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}