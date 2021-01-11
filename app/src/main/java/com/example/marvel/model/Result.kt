package com.example.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Result(

   val dates: List<Date>,
    val description: String?,
//    val diamondCode: String,
    val digitalId: Int,

    val id: Long,
    val images: List<Image>,

//    val modified: String,
   val pageCount: Int,
val prices: List<Price>,
  val resourceURI: String,

   val textObjects: List<TextObject>,

    val thumbnail: Thumbnail,
    val title: String,

   val variantDescription: String,

): Parcelable