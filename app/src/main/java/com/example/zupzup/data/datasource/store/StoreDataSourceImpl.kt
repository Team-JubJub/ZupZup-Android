package com.example.zupzup.data.datasource.store

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.example.zupzup.data.dto.Store
import com.example.zupzup.di.FireBaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.net.UnknownHostException
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    @FireBaseModule.StoreRef private val storeRef: CollectionReference,
    @ApplicationContext private val context: Context
) : StoreDataSource {

    override suspend fun getStoreList(): Result<List<Store>> {
        return runCatching {
            val connectivityManager =
                ContextCompat.getSystemService(context, ConnectivityManager::class.java)
            val currentNetwork = connectivityManager?.activeNetwork
            if (currentNetwork != null) {
                val storeList = storeRef.get().await().documents.mapNotNull {
                    it.toObject<Store>()
                }
                storeList
            } else {
                throw UnknownHostException()
            }
        }
    }

    override suspend fun getStoreDetailById(storeId: Long): Result<Store> {
        return runCatching {
            val store = storeRef.document(storeId.toString()).get().await().toObject<Store>()
            if (store != null) {
                store
            } else {
                throw NullPointerException()
            }
        }
    }
}