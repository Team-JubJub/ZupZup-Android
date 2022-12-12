package com.example.zupzup.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapViewEventListener(
    private val recyclerView: RecyclerView) : MapView.MapViewEventListener {
 // scrollview.requestDisallowInterceptTouchEvent(true);
    override fun onMapViewInitialized(p0: MapView?) {

    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }
}