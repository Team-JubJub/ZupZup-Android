package com.example.zupzup.ui.adaper.storedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.zupzup.databinding.ItemStoreDetailStoreInfoBinding
import com.example.zupzup.domain.models.StoreDetailHeaderModel
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class StoreDetailHeaderAdapter(
    private var storeDetailHeaderModel: StoreDetailHeaderModel? = null,
    private val activity: FragmentActivity
) :
    RecyclerView.Adapter<StoreDetailHeaderAdapter.StoreDetailHeaderViewHolder>() {

    fun setStoreDetailHeader(newStoreDetailHeader: StoreDetailHeaderModel) {
        storeDetailHeaderModel = newStoreDetailHeader
        notifyItemChanged(0)
    }

    class StoreDetailHeaderViewHolder(private val binding: ItemStoreDetailStoreInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StoreDetailHeaderModel?, activity: FragmentActivity) {
            if (item != null) {
                val mapView = getMapView(activity, item.location, item.name)
                binding.linearLayoutMapContainer.removeAllViews()
                binding.linearLayoutMapContainer.addView(mapView)
                binding.storeDetailHeader = item
                binding.executePendingBindings()
            }
        }

        private fun getMapView(
            activity: FragmentActivity,
            location: Pair<Double, Double>,
            storeName: String
        ): MapView {
            val mapView = MapView(activity)
            mapView.setMapCenterPointAndZoomLevel(
                MapPoint.mapPointWithGeoCoord(
                    location.first,
                    location.second
                ), 0, true
            )

            val marker = MapPOIItem()
            marker.itemName = storeName
            marker.tag = 0;
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(location.first, location.second)
            marker.markerType = MapPOIItem.MarkerType.BluePin
            mapView.addPOIItem(marker)
            return mapView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreDetailHeaderAdapter.StoreDetailHeaderViewHolder {
        val binding =
            ItemStoreDetailStoreInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StoreDetailHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StoreDetailHeaderViewHolder,
        position: Int
    ) {
        holder.bind(storeDetailHeaderModel, activity)
    }

    override fun getItemCount(): Int {
        return 1
    }
}