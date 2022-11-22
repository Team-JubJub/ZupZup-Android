package com.example.zupzup.domain.models

data class StoreDetailHeaderModel(
    val name: String,
    val location: Pair<Double, Double>,
    val openTime : String,
    val address: String,
    val saleTime: Pair<Int, Int>,
    val eventList: List<String>
)
