package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import kotlinx.coroutines.flow.Flow

interface GetStoreDetailUseCase {

    suspend operator fun invoke(storeId: Long): Flow<DataResult<StoreModel>>
}