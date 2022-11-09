package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zupzup.databinding.FragmentStoreDetailBinding
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
    }

    private fun setViewModelBinding() {
        with(binding) {
            viewModel = storeDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            helper = AmountManageHelper(
                storeDetailViewModel::increaseAmount,
                storeDetailViewModel::decreaseAmount
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


}