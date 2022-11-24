package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel

interface GetStoreDetailUseCase {

    suspend operator fun invoke(storeId: Long): DataResult<StoreModel>
}