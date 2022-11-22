package com.example.zupzup.data.datasource

import com.example.zupzup.data.dto.Store

interface StoreDataSource {
    suspend fun getStoreList(): Result<List<Store>>

    suspend fun getStoreDetailById(storeId: Long): Result<Store>
}