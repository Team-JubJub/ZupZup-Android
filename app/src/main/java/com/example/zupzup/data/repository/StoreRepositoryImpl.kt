package com.example.zupzup.data.repository

import com.example.zupzup.data.datamodel.Store
import com.example.zupzup.data.datasource.StoreDataSource
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource,
) : StoreRepository {

    private lateinit var dataResult: DataResult<List<StoreHeaderModel>>
    private lateinit var dataResult2: DataResult<StoreModel>

    override suspend fun getStoreList(): DataResult<List<StoreHeaderModel>> {
        storeDataSource.getStoreList()
            .onSuccess {
                dataResult = DataResult.Success(it.map { store -> store.toHeaderModel() })
            }.onFailure {
                dataResult = DataResult.Failure(it)
            }
        return dataResult
    }

    override suspend fun getStoreDetailById(storeId: Int): DataResult<StoreModel> {
        storeDataSource.getStoreDetailById(storeId)
            .onSuccess {
                dataResult2 = DataResult.Success(it.toModel())
            }
            .onFailure {
                dataResult2 = DataResult.Failure(it)
            }
        return dataResult2
    }
}