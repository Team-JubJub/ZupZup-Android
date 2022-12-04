package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import kotlinx.coroutines.flow.Flow

interface GetStoreListUseCase {
    suspend operator fun invoke(): Flow<DataResult<List<StoreHeaderInfoModel>>>
}