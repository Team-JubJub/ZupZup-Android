package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zupzup.databinding.FragmentStoreListBinding
import com.example.zupzup.ui.adaper.storelist.StoreListRecyclerViewAdapter
import com.example.zupzup.ui.viewmodel.StoreListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreListFragment : Fragment() {

    private var _binding: FragmentStoreListBinding? = null
    private val binding get() = _binding!!

    private val storeListViewModel: StoreListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = storeListViewModel
    }

    private fun initRecyclerView() {
        with(binding.rcvStoreList) {
            adapter =
                StoreListRecyclerViewAdapter { storeId: Long -> navigateStoreDetailFragment(storeId) }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateStoreDetailFragment(storeId: Long) {
        val action =
            StoreListFragmentDirections.actionFragStoreListToFragStoreDetail(storeId = storeId)
        findNavController().navigate(action)
    }
}