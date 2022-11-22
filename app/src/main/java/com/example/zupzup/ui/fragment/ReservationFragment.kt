package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zupzup.R
import com.example.zupzup.databinding.FragmentReservationBinding
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.adaper.reservation.ReservationBindingHelper
import com.example.zupzup.ui.adaper.reservation.ReservationCartListAdapter
import com.example.zupzup.ui.adaper.reservation.ReservationFooterAdapter
import com.example.zupzup.ui.adaper.reservation.ReservationHeaderAdapter
import com.example.zupzup.ui.viewmodel.ReservationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    private val args: ReservationFragmentArgs by navArgs()

    private val reservationViewModel: ReservationViewModel by viewModels()

    private lateinit var dataBindingHelper: ReservationBindingHelper

    private val headerAdapter: ReservationHeaderAdapter by lazy {
        ReservationHeaderAdapter()
    }

    private val footerAdapter: ReservationFooterAdapter by lazy {
        ReservationFooterAdapter(
            0, reservationViewModel.getCustomerInfo(),
            reservationViewModel::setIsApprove, ::showSetVisitTimeBottomSheet,
            ::showSetCustomerInfoBottomSheet, ::showTermsDetailDialog
        )
    }

    private val cartListAdapter: ReservationCartListAdapter by lazy {
        ReservationCartListAdapter()
    }

    private val concatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(headerAdapter, cartListAdapter, footerAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReservationBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initBinding()
        setArgumentToViewModel()
    }

    private fun showSetVisitTimeBottomSheet() {
        VisitTimeSetBottomSheet(
            Pair(args.start, args.end),
            reservationViewModel.getSelectedVisitTime(),
            reservationViewModel::setVisitTime
        ).show(parentFragmentManager, null)
    }

    private fun showSetCustomerInfoBottomSheet() {
        CustomerInfoSetBottomSheet(
            reservationViewModel.getCustomerInfo()
        ) { newCustomer -> reservationViewModel.setCustomerInfo(newCustomer) }
            .show(
                parentFragmentManager,
                null
            )
    }

    private fun initBinding() {
        dataBindingHelper = ReservationBindingHelper(
            ::navigateToReservationProcess,
            headerAdapter::setReservationHeader,
            footerAdapter::setReservationFooter,
            cartListAdapter::setReservationCartList
        )
        with(binding) {
            viewModel = reservationViewModel
            lifecycleOwner = viewLifecycleOwner
            bindingHelper = dataBindingHelper
        }
    }

    private fun initRecyclerView() {
        with(binding.rcvReservationInfo) {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
        }
    }

    private fun setArgumentToViewModel() {
        with(args) {
            reservationViewModel.setHeaderInfo(
                storeId, storeName, storeAddress, cartList.toList(), start, end
            )
        }
    }

    private fun showTermsDetailDialog() {
        val dialog = TermsDetailDialogFragment()
        dialog.show(parentFragmentManager, null)
    }

    private fun navigateToReservationProcess() {
        if (reservationViewModel.reservationUiState.value is UiState.Success) {
            val reservation =
                (reservationViewModel.reservationUiState.value as UiState.Success).data

            findNavController().navigate(
                R.id.action_frag_reservation_to_frag_reservation_process
            )
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}