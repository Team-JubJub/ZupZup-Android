package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class GetStoreDetailUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend operator fun invoke(storeId : Int): DataResult<StoreModel> =
        storeRepository.getStoreDetailById(storeId)
}