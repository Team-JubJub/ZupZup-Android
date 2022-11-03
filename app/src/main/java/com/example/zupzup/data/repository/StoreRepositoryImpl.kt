package com.example.zupzup.data.repository

import com.example.zupzup.data.datamodel.Store
import com.example.zupzup.data.datasource.StoreDataSource
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource
) : StoreRepository {

    private lateinit var dataResult: DataResult<List<StoreModel>>

    override suspend fun getStoreList(): DataResult<List<StoreModel>> {
        storeDataSource.getStoreList()
            .onSuccess {
                dataResult = DataResult.Success(it.map { store -> store.toModel() })
            }.onFailure {
                dataResult = DataResult.Failure(it)
            }
        return dataResult
    }

    private fun Store.toModel(): StoreModel {
        return StoreModel(
            storeId = storeId,
            storeType = storeType,
            storeName = storeName,
            storeSalesInfo = storeSalesInfo,
            storeOpenTime = storeOpenTime
        )
    }
}