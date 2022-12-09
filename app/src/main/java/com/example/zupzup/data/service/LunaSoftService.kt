package com.example.zupzup.data.service

import com.example.zupzup.data.dto.lunasoft.parameter.LunaSoftRequestBody
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import retrofit2.Response
import retrofit2.http.*

interface LunaSoftService {

    @POST("api/AlimTalk/message/send")
    suspend fun sendNotificationTalk(
        @Body parameter : LunaSoftRequestBody
    ): Response<LunaSoftResponse>
}