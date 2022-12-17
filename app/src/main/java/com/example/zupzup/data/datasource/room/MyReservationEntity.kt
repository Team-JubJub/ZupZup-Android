package com.example.zupzup.data.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyReservationEntity(
    @PrimaryKey @ColumnInfo(name = "reserve_id") val reserveId : Long,
    @ColumnInfo(name = "store_name") val storeName : String,
    @ColumnInfo(name = "store_address") val storeAddress : String
)
