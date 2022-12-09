package com.example.zupzup.data.repository

import android.content.Context
import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.domain.NetworkManager
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.UnknownHostException
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource,
    @ApplicationContext private val context: Context
) : StoreRepository {

    override suspend fun getStoreList(): Result<List<StoreHeaderInfoModel>> {
        return try {
            if (!NetworkManager(context).isNetworkConnected()) {
                throw UnknownHostException()
            }
            Result.success(storeDataSource.getStoreList().map { it.toHeaderInfoModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStoreDetailById(storeId: Long): Result<StoreModel> {
        return try {
            if (!NetworkManager(context).isNetworkConnected()) {
                throw UnknownHostException()
            }
            Result.success(storeDataSource.getStoreDetailById(storeId).toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}