package com.example.testapplication.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    var code: String? = null,
    var description: String? = null,
    var userMessage: String? = null
) : Parcelable