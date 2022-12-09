package com.example.zupzup.domain

import java.net.UnknownHostException

object ErrorMapper {

    fun getErrorCode(e: Throwable): Int {
        return when (e) {
            is UnknownHostException -> 1000
            is NullPointerException -> 1001
            else -> 5000
        }
    }
}