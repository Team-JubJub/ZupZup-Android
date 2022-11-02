package com.example.zupzup.data.datamodel

data class StoreHeaderInfo(
    val storeAddress : String,
    val storeType : String,
    val storeName: String,
    val storeSalesInfo: Int,
    val storeOpenTime: String,
    val storeEventList : List<String>
)
