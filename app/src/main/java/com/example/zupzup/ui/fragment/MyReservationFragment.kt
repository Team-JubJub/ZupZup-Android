package com.example.zupzup.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zupzup.databinding.FragmentMyReservationBinding
import com.example.zupzup.domain.models.MyReservationModel
import com.example.zupzup.ui.adaper.myreservation.MyReservationListAdapter
import com.example.zupzup.ui.viewmodel.MyReservationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyReservationFragment : Fragment() {

    private var _binding: FragmentMyReservationBinding? = null
    private val binding get() = _binding!!

    private val myReservationViewModel: MyReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyReservationBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initRecyclerView()
    }

    private fun navigateToMyReservationDetail(myReservationModel: MyReservationModel) {
        val action = MyReservationFragmentDirections.actionFragMyReservationToMyReservationDetailFragment(
            myReservation = myReservationModel
        )
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        with(binding.rcvMyReservationList) {
            adapter = MyReservationListAdapter(::navigateToMyReservationDetail)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initBinding() {
        with(binding) {
            viewModel = myReservationViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}