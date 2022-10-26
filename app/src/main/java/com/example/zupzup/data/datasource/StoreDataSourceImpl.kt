package com.example.zupzup.data.datasource

import com.example.zupzup.domain.DataResult
import com.example.zupzup.data.datamodel.Store
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(): StoreDataSource {
    override suspend fun getStoreList(): Result<List<Store>> {
        val dataList =  listOf<Store>(
            Store(1, "카페", "로그인 카페", 40, "12:00 ~ 18:00"),
            Store(2, "카페", "AM 11:00", 40, "12:00 ~ 18:00"),
            Store(3, "카페", "번아웃 커피 금정점", 40, "12:00 ~ 18:00")
        )
        return Result.success(dataList)
    }
}