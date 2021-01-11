package com.example.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date (

       val type: String,
       val date: String
):Parcelable
