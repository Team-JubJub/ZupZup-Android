package com.example.zupzup.data.datasource

import com.example.zupzup.data.TestData
import com.example.zupzup.data.datamodel.Store
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor() : StoreDataSource {
    override suspend fun getStoreList(): Result<List<Store>> {
        val storeList = TestData.storeList
        return Result.success(storeList)
    }

    override suspend fun getStoreDetailById(storeId: Int): Result<Store> {
        val store = TestData.storeList.find { it.storeID == storeId }
        return Result.success(store!!)
    }
}