package com.example.zupzup.domain.models

import android.os.Parcel
import android.os.Parcelable

data class CartModel(
    val itemId: Long = 0,
    val storeId: Long = 0,
    val itemName: String? = "",
    val salesPrice: Int = 0,
    val amount: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(itemId)
        parcel.writeLong(storeId)
        parcel.writeString(itemName)
        parcel.writeInt(salesPrice)
        parcel.writeInt(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartModel> {
        override fun createFromParcel(parcel: Parcel): CartModel {
            return CartModel(parcel)
        }

        override fun newArray(size: Int): Array<CartModel?> {
            return arrayOfNulls(size)
        }
    }
}
