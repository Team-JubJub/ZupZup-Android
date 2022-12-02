package com.example.zupzup.data.datasource.room.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.zupzup.data.dto.Cart
import com.google.gson.Gson


@ProvidedTypeConverter
class TypeConverter(private val gson: Gson) {

    @TypeConverter
    fun cartToJson(value: Cart): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToCart(value: String): Cart {
        return gson.fromJson(value, Cart::class.java)
    }

    @TypeConverter
    fun cartListToJson(value: List<Cart>): String {
        val converted = value.map {
            cartToJson(it)
        }
        return gson.toJson(converted)
    }


    @TypeConverter
    fun jsonToList(value: String): List<String> {
        return gson.fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun jsonToCartList(value: String): List<Cart> {
        val stringList = jsonToList(value)
        return stringList.map {
            jsonToCart(it)
        }
    }

}