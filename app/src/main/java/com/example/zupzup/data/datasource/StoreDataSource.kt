package com.example.zupzup.data.datasource

import com.example.zupzup.data.datamodel.Store

interface StoreDataSource {
    suspend fun getStoreList(): Result<List<Store>>

    suspend fun getStoreDetailById(storeId: Int): Result<Store>
}