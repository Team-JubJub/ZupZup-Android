package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.example.zupzup.databinding.FragmentReservationCompleteBinding
import com.example.zupzup.domain.models.CustomerModel
import com.example.zupzup.domain.models.ReservationHeaderModel

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
            binding.reservationHeader = ReservationHeaderModel(
                0,
                storeName,
                storeAddress,
                cartList.toList(),
                Pair(0, 0)
            )
            binding.customer = CustomerModel(customerName, customerPhoneNumber)
            binding.visitTime = visitTime
            binding.inflater = requireActivity().layoutInflater
            binding.navigateHome = ::navigateHome
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}