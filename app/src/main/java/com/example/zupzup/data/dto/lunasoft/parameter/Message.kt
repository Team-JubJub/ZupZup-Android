package com.example.zupzup.data.dto.lunasoft.parameter

import com.google.gson.annotations.SerializedName

data class Message(
    val no : String,
    @SerializedName("tel_num") val telNum : String,
    @SerializedName("msg_content") val msgContent : String,
    @SerializedName("sms_content") val smsContent : String,
    @SerializedName("use_sms") val useSms : String = "0",
    @SerializedName("btn_url") val btnUrl : List<Map<String,String>> = listOf(
        mapOf("url_pc" to  "http://instabio.cc/zupzupplace", "url_mobile" to  "https://instabio.cc/zupzupplace")
    )
)
