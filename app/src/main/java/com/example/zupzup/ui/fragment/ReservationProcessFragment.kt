package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.zupzup.databinding.FragmentReservationProcessBinding
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel
import com.example.zupzup.ui.bindinghelper.ReservationProcessBindingHelper
import com.example.zupzup.ui.viewmodel.ReservationProcessViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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
            reservationProcessViewModel.makeReservation(
                ReservationHeaderModel(
                    storeId,
                    storeName,
                    storeAddress,
                    cartList.toList(),
                    Pair(0, 0)
                ),
                visitTime, CustomerModel(customerName, customerPhoneNumber), hostPhoneNumber
            )
        }
    }

    private fun navigateReservationCompleteFragment() {
        with(args) {
            val action =
                ReservationProcessFragmentDirections.actionFragReservationProcessToReservationCompleteFragment(
                    storeName = storeName,
                    storeAddress = storeAddress,
                    cartList = cartList,
                    customerName = customerName,
                    customerPhoneNumber = customerPhoneNumber,
                    visitTime = visitTime

                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}