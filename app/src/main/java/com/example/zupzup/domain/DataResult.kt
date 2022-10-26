package com.example.zupzup.domain

sealed class DataResult<out T> {
    data class Success<out T> (val data : T) : DataResult<T>()
    data class Failure(val error : Throwable) : DataResult<Nothing>()
}
