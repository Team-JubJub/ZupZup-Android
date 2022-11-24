package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel

interface GetStoreListUseCase {
    suspend operator fun invoke(): DataResult<List<StoreHeaderInfoModel>>
}