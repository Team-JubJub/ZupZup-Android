package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zupzup.databinding.FragmentStoreDetailBinding
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.MerchandiseModel
import com.example.zupzup.domain.models.ReservationHeaderModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.adaper.storedetail.StoreDetailBodyAdapter
import com.example.zupzup.ui.adaper.storedetail.StoreDetailHeaderAdapter
import com.example.zupzup.ui.bindinghelper.AmountManageHelper
import com.example.zupzup.ui.bindinghelper.StoreDetailBindingHelper
import com.example.zupzup.ui.viewmodel.StoreDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreDetailFragment : Fragment() {

    private var _binding: FragmentStoreDetailBinding? = null
    private val binding get() = _binding!!

    private val storeDetailViewModel: StoreDetailViewModel by viewModels()
    private val args: StoreDetailFragmentArgs by navArgs()

    private val headerAdapter: StoreDetailHeaderAdapter by lazy {
        StoreDetailHeaderAdapter(activity = requireActivity())
    }

    private val bodyAdapter: StoreDetailBodyAdapter by lazy {
        StoreDetailBodyAdapter(
            AmountManageHelper(
                storeDetailViewModel::increaseAmount,
                storeDetailViewModel::decreaseAmount,
                storeDetailViewModel::getAmountList
            )
        )
    }

    private val concatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(headerAdapter, bodyAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStoreDetailBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initRcvLayoutManager()
        getStoreDetailById()
    }

    private fun initBinding() {
        with(binding) {
            viewModel = storeDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            bindingHelper = StoreDetailBindingHelper(
                headerAdapter::setStoreDetailHeader,
                bodyAdapter::submitList,
                navigateToBackStack = ::navigateBackStack,
                navigateToReservation = ::navigateToReservationFragment
            )
        }
    }

    private fun initRcvLayoutManager() {
        with(binding.rcvStoreDetail) {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getStoreDetailById() {
        val storeId = args.storeId
        storeDetailViewModel.getStoreDetailById(storeId)
    }

    private fun navigateToReservationFragment() {
        if (storeDetailViewModel.storeDetailUiState.value is UiState.Success<StoreModel>) {
            val uiState =
                storeDetailViewModel.storeDetailUiState.value as UiState.Success<StoreModel>
            with(uiState.data) {
                val cartList = makeCartList(merchandiseList)
                val action =
                    StoreDetailFragmentDirections.actionFragStoreDetailToFragReservation(
                        reservationHeader = ReservationHeaderModel(
                            storeId,
                            name,
                            address,
                            cartList,
                            Pair(saleTime.first, saleTime.second)
                        ),
                        hostPhoneNumber = hostPhoneNumber
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun makeCartList(merchandiseList: List<MerchandiseModel>): List<CartModel> {
        val amountList = storeDetailViewModel.getAmountList()
        val cartList = arrayListOf<CartModel>()
        amountList.forEachIndexed { idx, amount ->
            if (amount > 0) {
                cartList.add(merchandiseList[idx].toCartModel(amount))
            }
        }
        return cartList
    }

    private fun navigateBackStack() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}