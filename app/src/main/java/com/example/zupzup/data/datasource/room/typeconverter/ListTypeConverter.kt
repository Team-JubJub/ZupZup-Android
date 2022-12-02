package com.example.zupzup.data.datasource.room.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson

@ProvidedTypeConverter
class ListTypeConverter(private val gson : Gson) {

    @TypeConverter
    fun listToJson(value : List<String>) : String {
        return gson.toJson(value)
    }

}