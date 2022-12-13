package com.example.zupzup.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapViewEventListener(
    private val recyclerView: RecyclerView) : MapView.MapViewEventListener {

    override fun onMapViewInitialized(p0: MapView?) {
    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
    }
}