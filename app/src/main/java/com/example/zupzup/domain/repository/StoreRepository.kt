package com.example.zupzup.domain.repository

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel

interface StoreRepository {
    suspend fun getStoreList(): DataResult<List<StoreHeaderInfoModel>>
    suspend fun getStoreDetailById(storeId : Int): DataResult<StoreModel>
}