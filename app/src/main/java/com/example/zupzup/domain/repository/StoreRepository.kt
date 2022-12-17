package com.example.zupzup.domain.repository

import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel

interface StoreRepository {
    suspend fun getStoreList(): Result<List<StoreHeaderInfoModel>>
    suspend fun getStoreDetailById(storeId: Long): Result<StoreModel>
}