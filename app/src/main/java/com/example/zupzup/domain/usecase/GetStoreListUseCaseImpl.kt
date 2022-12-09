package com.example.zupzup.domain.usecase

import com.example.zupzup.domain.DataResult
import com.example.zupzup.domain.ErrorMapper
import com.example.zupzup.domain.models.StoreHeaderInfoModel
import com.example.zupzup.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetStoreListUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : GetStoreListUseCase {
    override suspend operator fun invoke(): Flow<DataResult<List<StoreHeaderInfoModel>>> {
        return flow {
            storeRepository.getStoreList().onSuccess {
                emit(DataResult.Success(it))
            }.onFailure {
                emit(DataResult.Failure(ErrorMapper.getErrorCode(it)))
            }
        }.flowOn(Dispatchers.IO)
    }
}

