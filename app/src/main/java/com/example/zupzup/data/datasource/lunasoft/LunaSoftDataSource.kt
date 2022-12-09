package com.example.zupzup.data.datasource.lunasoft

import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import retrofit2.Response

interface LunaSoftDataSource {

    suspend fun sendNotificationTalk(messages: List<Message>): Response<LunaSoftResponse>
}