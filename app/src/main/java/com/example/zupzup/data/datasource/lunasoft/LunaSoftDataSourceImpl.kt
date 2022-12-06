package com.example.zupzup.data.datasource.lunasoft


import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.example.zupzup.data.dto.lunasoft.parameter.LunaSoftRequestBody
import com.example.zupzup.data.dto.lunasoft.parameter.Message
import com.example.zupzup.data.dto.lunasoft.response.LunaSoftResponse
import com.example.zupzup.data.service.LunaSoftService
import com.example.zupzup.utils.Constants.apiKey
import com.example.zupzup.utils.Constants.defaultTemplateId
import com.example.zupzup.utils.Constants.userId
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.UnknownHostException
import javax.inject.Inject

class LunaSoftDataSourceImpl @Inject constructor(
    private val lunaSoftService: LunaSoftService,
    @ApplicationContext private val context: Context
) : LunaSoftDataSource {

    override suspend fun sendNotificationTalk(messages: List<Message>): Result<LunaSoftResponse> {
        return runCatching {
            val connectivityManager =
                ContextCompat.getSystemService(context, ConnectivityManager::class.java)
            val currentNetwork = connectivityManager?.activeNetwork
            if (currentNetwork != null) {
//                val body = LunaSoftRequestBody(
//                    userId = userId,
//                    apiKey = apiKey,
//                    templateId = defaultTemplateId,
//                    messages = messages
//                )
//                val result = lunaSoftService.sendNotificationTalk(
//                    parameter = body
//                )
//                result
                LunaSoftResponse(0,null)
            } else {
                throw UnknownHostException()
            }
        }
    }
}