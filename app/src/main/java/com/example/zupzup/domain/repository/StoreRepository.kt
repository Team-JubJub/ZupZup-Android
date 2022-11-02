package com.example.zupzup.domain.repository

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.domain.models.StoreModel

interface StoreRepository {
    suspend fun getStoreList(): DataResult<List<StoreHeaderModel>>
    suspend fun getStoreDetailById(storeId : Int): DataResult<StoreModel>
}