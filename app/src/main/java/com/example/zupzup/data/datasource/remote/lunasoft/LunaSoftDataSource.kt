package com.example.zupzup.data.datasource.remote.lunasoft

import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse

interface LunaSoftDataSource {

    suspend fun sendNotificationTalk(messages: List<Message>): Result<LunaSoftResponse>
}