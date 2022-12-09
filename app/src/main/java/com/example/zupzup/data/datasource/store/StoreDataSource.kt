package com.example.zupzup.data.datasource.store

import com.example.zupzup.data.dto.Cart
import com.example.zupzup.data.dto.Store

interface StoreDataSource {
    suspend fun getStoreList(): List<Store>

    suspend fun getStoreDetailById(storeId: Long): Store

    suspend fun updateMerchandiseStock(storeId: Long, cartList : List<Cart>)
}