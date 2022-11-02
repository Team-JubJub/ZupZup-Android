package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class GetStoreListUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend operator fun invoke(): DataResult<List<StoreHeaderModel>> =
        storeRepository.getStoreList()
}