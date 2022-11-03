package com.example.zupzup.domain.models

data class StoreHeaderModel(
    val storeId: Int,
    val storeAddress: String,
    val storeType: String,
    val storeName: String,
    val storeSalesInfo: Int,
    val storeOpenTime: String,
    val storeEventList : List<String>
)