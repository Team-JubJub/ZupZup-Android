package com.example.zupzup.domain.repository

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getStoreList(): Flow<DataResult<List<StoreHeaderInfoModel>>>
    suspend fun getStoreDetailById(storeId: Long): Flow<DataResult<StoreModel>>
}