package com.example.zupzup.data.datasource

import com.example.zupzup.data.datamodel.Store
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    private val storeRef: CollectionReference
) : StoreDataSource {
    override suspend fun getStoreList(): Result<List<Store>> {
        return try {
            val storeList = storeRef.get().await().documents.mapNotNull {
                it.toObject<Store>()
            }
            Result.success(storeList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStoreDetailById(storeId: Long): Result<Store> {
        return try {
            val store = storeRef.document(storeId.toString()).get().await().toObject<Store>()
            Result.success(store!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}