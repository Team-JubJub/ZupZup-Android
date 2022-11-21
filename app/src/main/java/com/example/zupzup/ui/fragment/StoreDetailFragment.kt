package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zupzup.databinding.FragmentStoreDetailBinding
import com.example.zupzup.domain.models.CartModel
import com.example.zupzup.domain.models.MerchandiseModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.ui.UiState
import com.example.zupzup.ui.utils.AmountManageHelper
import com.example.zupzup.ui.viewmodel.StoreDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreDetailFragment : Fragment() {

    private var _binding: FragmentStoreDetailBinding? = null
    private val binding get() = _binding!!

    private val storeDetailViewModel: StoreDetailViewModel by viewModels()
    private val args: StoreDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStoreDetailBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelBinding()
        initRcvLayoutManager()
        getStoreDetailById()
        navigateToReservationFragment()
    }

    private fun setViewModelBinding() {
        with(binding) {
            viewModel = storeDetailViewModel
            lifecycleOwner = viewLifecycleOwner

            helper = AmountManageHelper(
                storeDetailViewModel::increaseAmount,
                storeDetailViewModel::decreaseAmount,
                storeDetailViewModel::getAmountList
            )
        }
    }

    private fun initRcvLayoutManager() {
        binding.rcvStoreDetail.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getStoreDetailById() {
        val storeId = args.storeId
        storeDetailViewModel.getStoreDetailById(storeId)
    }

    private fun navigateToReservationFragment() {
        binding.btnMakeReservation.setOnClickListener {
            if (storeDetailViewModel.storeDetailUiState.value is UiState.Success<StoreModel>) {
                val uiState =
                    storeDetailViewModel.storeDetailUiState.value as UiState.Success<StoreModel>
                val storeModel = uiState.data
                val storeId = storeModel.storeID
                val storeName = storeModel.headerInfo.name
                val storeAddress = storeModel.address
                val cartList = makeCartList(storeModel.merchandiseList)
                val saleTime = storeModel.saleTime
                val action = StoreDetailFragmentDirections.actionFragStoreDetailToFragReservation(
                    storeId,
                    storeName,
                    storeAddress,
                    cartList,
                    saleTime.first,
                    saleTime.second
                )
                if (cartList.isEmpty()) {
                    Toast.makeText(requireContext(), "상품을 선택해주세요 !", LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun makeCartList(merchandiseList: List<MerchandiseModel>): Array<CartModel> {
        val amountList = storeDetailViewModel.getAmountList()
        val cartList = arrayListOf<CartModel>()
        amountList.forEachIndexed { idx, amount ->
            if (amount > 0) {
                cartList.add(merchandiseList[idx].toCartModel(amount))
            }
        }
        return cartList.toTypedArray()
    }

}