package com.example.zupzup.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomerModel(
    val name: String,
    val phoneNumber: String
) : Parcelable