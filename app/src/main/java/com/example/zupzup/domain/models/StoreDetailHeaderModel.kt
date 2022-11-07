package com.example.zupzup.domain.models

data class StoreDetailHeaderModel(
    val headerInfo: StoreHeaderInfoModel,
    val address : String,
    val eventList : List<String>,

)
