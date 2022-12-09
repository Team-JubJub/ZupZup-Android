package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.models.StoreModel
import com.example.zupzup.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetStoreDetailUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : GetStoreDetailUseCase {
    override suspend operator fun invoke(storeId: Long): Flow<DataResult<StoreModel>> {
        return flow {
            storeRepository.getStoreDetailById(storeId).onSuccess {
                emit(DataResult.Success(it))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
        }.flowOn(Dispatchers.IO)
    }
}