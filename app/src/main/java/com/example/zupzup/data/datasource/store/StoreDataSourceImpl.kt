package com.example.zupzup.data.datasource.store

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.example.zupzup.data.dto.Cart
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

    private val connectivityManager =
        ContextCompat.getSystemService(context, ConnectivityManager::class.java)

    override suspend fun getStoreList(): Result<List<Store>> {
        return runCatching {
            if (connectivityManager?.activeNetwork != null) {
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
            if (connectivityManager?.activeNetwork != null) {
                val store = storeRef.document(storeId.toString()).get().await().toObject<Store>()
                if (store != null) {
                    store
                } else {
                    throw NullPointerException()
                }
            } else {
                throw UnknownHostException()
            }
        }
    }

    override suspend fun updateMerchandiseStock(storeId: Long, cartList: List<Cart>): Result<Long> {
        return runCatching {
            if (connectivityManager?.activeNetwork != null) {
                val store = storeRef.document(storeId.toString()).get().await().toObject<Store>()
                if (store != null) {
                    val merchandiseList = store.merchandiseList
                    cartList.forEach { cart ->
                        val item = merchandiseList.find { it.itemId == cart.itemId }
                        if (item != null) {
                            item.stock -= cart.amount
                        }
                    }
                    storeRef.document(storeId.toString())
                        .update(mapOf("merchandiseList" to merchandiseList)).await()
                }
                0
            } else {
                throw UnknownHostException()
            }
        }

    }
}