package com.example.zupzup.domain.models


data class StoreModel(
    val storeId: Int,
    val headerInfo: StoreHeaderModel,
    val merchandiseList: List<MerchandiseModel>
)
