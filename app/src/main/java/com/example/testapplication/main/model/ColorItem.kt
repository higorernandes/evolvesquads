package com.example.testapplication.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Colors(
    var colors: MutableList<ColorItem>?
) : Parcelable

@Parcelize
data class ColorItem(
    var name: String?,
    var hex: String
) : Parcelable