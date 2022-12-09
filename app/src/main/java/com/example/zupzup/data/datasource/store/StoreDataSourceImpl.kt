package com.example.zupzup.data.datasource.store

import com.example.zupzup.data.dto.Cart
import com.example.zupzup.data.dto.Store
import com.example.zupzup.di.FireBaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    @FireBaseModule.StoreRef private val storeRef: CollectionReference
) : StoreDataSource {

    override suspend fun getStoreList(): List<Store> {
        return storeRef.get().await().mapNotNull { it.toObject() }
    }

    override suspend fun getStoreDetailById(storeId: Long): Store {
        val store = storeRef.document(storeId.toString()).get().await()
        if (store != null) {
            return store.toObject<Store>()!!
        } else {
            throw NullPointerException()
        }
    }

    override suspend fun updateMerchandiseStock(storeId: Long, cartList: List<Cart>) {
        val store = storeRef.document(storeId.toString()).get().await()
        if (store != null) {
            val merchandiseList = store.toObject<Store>()!!.merchandiseList
            cartList.forEach { cart ->
                val item =
                    merchandiseList.find { merchandise -> merchandise.itemId == cart.itemId }
                if (item != null) {
                    item.stock -= cart.amount
                    if (item.stock < 0) {
                        item.stock = 0
                    }
                }
            }
            storeRef.document(storeId.toString())
                .update(mapOf("merchandiseList" to merchandiseList)).await()
        } else {
            throw NullPointerException()
        }
    }
}