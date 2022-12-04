package com.example.zupzup.ui.adaper.myreservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemMyReservationListBinding
import com.example.zupzup.domain.models.MyReservationModel

class MyReservationListAdapter(
    private val navigateMyReservationDetail: (MyReservationModel) -> Unit
) :
    ListAdapter<MyReservationModel, MyReservationListAdapter.MyReservationViewHolder>(DiffCallBack) {

    object DiffCallBack : DiffUtil.ItemCallback<MyReservationModel>() {
        override fun areItemsTheSame(
            oldItem: MyReservationModel,
            newItem: MyReservationModel
        ): Boolean {
            return oldItem.reserveId == newItem.reserveId
        }

        override fun areContentsTheSame(
            oldItem: MyReservationModel,
            newItem: MyReservationModel
        ): Boolean {
            return oldItem.reserveId == newItem.reserveId
        }
    }

    class MyReservationViewHolder(
        private val binding: ItemMyReservationListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyReservationModel, navigate: (MyReservationModel) -> Unit) {
            with(binding) {
                myReservation = item
                navigateToMyReservationDetail = navigate
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReservationViewHolder {
        val binding =
            ItemMyReservationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyReservationViewHolder, position: Int) {
        val myReservation = getItem(position)
        holder.bind(myReservation, navigateMyReservationDetail)
    }
}