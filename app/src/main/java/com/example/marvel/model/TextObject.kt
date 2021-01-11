package com.example.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TextObject(
    val language: String,
    val text: String,
    val type: String
): Parcelable