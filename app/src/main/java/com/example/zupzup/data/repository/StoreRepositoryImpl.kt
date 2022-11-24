package com.example.zupzup.data.repository

import com.example.zupzup.data.datasource.ReservationDataSource
import com.example.zupzup.data.datasource.StoreDataSource
import com.example.zupzup.data.dto.Reservation
import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource
) : StoreRepository {

    private lateinit var storeListResult: DataResult<List<StoreHeaderInfoModel>>
    private lateinit var storeDetailResult: DataResult<StoreModel>

    override suspend fun getStoreList(): DataResult<List<StoreHeaderInfoModel>> {
        storeDataSource.getStoreList()
            .onSuccess {
                storeListResult = DataResult.Success(it.map { store ->
                    store.toHeaderInfoModel()
                })
            }.onFailure {
                storeListResult = DataResult.Failure(it)
            }
        return storeListResult
    }

    override suspend fun getStoreDetailById(storeId: Long): DataResult<StoreModel> {
        storeDataSource.getStoreDetailById(storeId)
            .onSuccess {
                storeDetailResult = DataResult.Success(it.toModel())
            }
            .onFailure {
                storeDetailResult = DataResult.Failure(it)
            }
        return storeDetailResult
    }
}