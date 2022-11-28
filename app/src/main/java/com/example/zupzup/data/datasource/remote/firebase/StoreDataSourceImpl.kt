package com.example.zupzup.data.datasource.remote.firebase

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.example.zupzup.data.dto.Store
import com.example.zupzup.di.FireBaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    @FireBaseModule.StoreRef private val storeRef: CollectionReference,
    @ApplicationContext private val context: Context
) : StoreDataSource {

    override suspend fun getStoreList(): Result<List<Store>> {
        return try {
            val connectivityManager =
                ContextCompat.getSystemService(context, ConnectivityManager::class.java)
            val currentNetwork = connectivityManager?.activeNetwork
            if (currentNetwork != null) {
                withContext(Dispatchers.IO) {
                    val storeList = storeRef.get().await().documents.mapNotNull {
                        it.toObject<Store>()
                    }
                    Result.success(storeList)
                }
            } else {
                throw UnknownHostException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStoreDetailById(storeId: Long): Result<Store> {
        return try {
            val store = storeRef.document(storeId.toString()).get().await().toObject<Store>()
            if (store != null) {
                Result.success(store)
            } else {
                throw NullPointerException()
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}