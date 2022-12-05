package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.zupzup.databinding.FragmentReservationProcessBinding
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.ui.bindinghelper.ReservationProcessBindingHelper
import com.example.zupzup.ui.viewmodel.ReservationProcessViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationProcessFragment : Fragment() {

    private var _binding: FragmentReservationProcessBinding? = null
    private val binding get() = _binding!!

    private val args: ReservationProcessFragmentArgs by navArgs()
    private val reservationProcessViewModel: ReservationProcessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReservationProcessBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        makeReservation()
    }

    private fun initBinding() {
        with(binding) {
            viewModel = reservationProcessViewModel
            lifecycleOwner = viewLifecycleOwner
            bindingHelper = ReservationProcessBindingHelper(::navigateReservationCompleteFragment)
        }
    }

    private fun makeReservation() {
        with(args) {
            reservationProcessViewModel.makeReservation(reservation, hostPhoneNumber)
        }
    }

    private fun navigateReservationCompleteFragment() {
        with(args.reservation) {
            val action =
                ReservationProcessFragmentDirections.actionFragReservationProcessToReservationCompleteFragment(
                    MyReservationModel(
                        0,
                        reservationHeaderInfo.storeName,
                        reservationHeaderInfo.storeAddress,
                        visitTime,
                        reservationHeaderInfo.cartList,
                        customer
                    )
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}