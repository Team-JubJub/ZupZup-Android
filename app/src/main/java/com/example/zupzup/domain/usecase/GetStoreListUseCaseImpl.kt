package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.repository.StoreRepository
import javax.inject.Inject

class GetStoreListUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : GetStoreListUseCase {
    override suspend operator fun invoke(): DataResult<List<StoreHeaderInfoModel>> =
        storeRepository.getStoreList()
}