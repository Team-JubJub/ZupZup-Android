package com.example.zupzup.data.api

import com.example.zupzup.data.dto.lunasoft.parameter.LunaSoftRequestBody
import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import retrofit2.http.*

interface LunaSoftService {


    @Headers("Content-type:application/json")
    @POST("api/AlimTalk/message/send")
    suspend fun sendNotificationTalk(
        @Body parameter : LunaSoftRequestBody
    ): LunaSoftResponse
}