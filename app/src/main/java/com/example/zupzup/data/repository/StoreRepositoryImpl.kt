package com.example.zupzup.data.repository

import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource,
) : StoreRepository {

    override suspend fun getStoreList(): Result<List<StoreHeaderInfoModel>> {
        return try {
            Result.success(storeDataSource.getStoreList().map { it.toHeaderInfoModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStoreDetailById(storeId: Long): Result<StoreModel> {
        return try {
            Result.success(storeDataSource.getStoreDetailById(storeId).toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}