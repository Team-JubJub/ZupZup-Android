package com.example.zupzup.data.datasource.lunasoft


import android.util.Log
import com.example.zupzup.data.dto.lunasoft.parameter.LunaSoftRequestBody
import com.example.zupzup.data.service.LunaSoftService
import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import com.example.zupzup.utils.Constants.apiKey
import com.example.zupzup.utils.Constants.defaultTemplateId
import com.example.zupzup.utils.Constants.userId
import javax.inject.Inject

class LunaSoftDataSourceImpl @Inject constructor(
    private val lunaSoftService: LunaSoftService
) : LunaSoftDataSource {

    override suspend fun sendNotificationTalk(messages: List<Message>): Result<LunaSoftResponse> {
        return try {
            val body = LunaSoftRequestBody(
                userId = userId,
                apiKey = apiKey,
                templateId = defaultTemplateId,
                messages = messages
            )
            Log.d("TAG", "$body")
            val result = lunaSoftService.sendNotificationTalk(
                parameter = body
            )
            Log.d("TAG", "Lunasoft result $result ")
            Result.success(result)
              //Result.success(LunaSoftResponse(0, null))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}