package com.example.zupzup.data.datasource.lunasoft


import android.content.Context
import com.example.zupzup.data.dto.lunasoft.parameter.LunaSoftRequestBody
import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import com.example.zupzup.data.service.LunaSoftService
import com.example.zupzup.utils.Constants.apiKey
import com.example.zupzup.utils.Constants.defaultTemplateId
import com.example.zupzup.utils.Constants.userId
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class LunaSoftDataSourceImpl @Inject constructor(
    private val lunaSoftService: LunaSoftService,
    @ApplicationContext private val context: Context
) : LunaSoftDataSource {

    override suspend fun sendNotificationTalk(messages: List<Message>): Response<LunaSoftResponse> {
        val body = LunaSoftRequestBody(
            userId = userId,
            apiKey = apiKey,
            templateId = defaultTemplateId,
            messages = messages
        )
        return lunaSoftService.sendNotificationTalk(
            parameter = body
        )
        //return Response.success(LunaSoftResponse(0,null))
    }
}