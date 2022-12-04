package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreDetailUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : GetStoreDetailUseCase {
    override suspend operator fun invoke(storeId: Long): Flow<DataResult<StoreModel>> {
        return storeRepository.getStoreDetailById(storeId)
    }
}