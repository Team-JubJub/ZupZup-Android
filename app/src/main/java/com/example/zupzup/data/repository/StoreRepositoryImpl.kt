package com.example.zupzup.data.repository

import com.example.zupzup.data.datasource.store.StoreDataSource
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource
) : StoreRepository {

    override suspend fun getStoreList(): Flow<DataResult<List<StoreHeaderInfoModel>>> {
        return flow {
            storeDataSource.getStoreList()
                .onSuccess {
                    emit(DataResult.Success(it.map { store ->
                        store.toHeaderInfoModel()
                    }))
                }.onFailure {
                    emit(DataResult.Failure(it))
                }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getStoreDetailById(storeId: Long): Flow<DataResult<StoreModel>> {
        return flow {
            storeDataSource.getStoreDetailById(storeId)
                .onSuccess {
                    emit(DataResult.Success(it.toModel()))
                }
                .onFailure {
                    emit(DataResult.Failure(it))
                }
        }.flowOn(Dispatchers.IO)
    }
}