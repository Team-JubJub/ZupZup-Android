package com.example.zupzup.domain

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import javax.inject.Singleton

@Singleton
class NetworkManager(context: Context) {
    private val connectivityManager = getSystemService(context, ConnectivityManager::class.java)

    fun isNetworkConnected(): Boolean {
        return if (connectivityManager == null) {
            false
        } else {
            connectivityManager.activeNetwork != null
        }
    }
}