package com.example.marvel

fun String.FullPath(extension: String,size:String):String{
return "${this}/$size.${extension}"
}


fun String.getYear(): String {
    val dateList = this.split("-")
    return "${dateList[1]} , ${dateList[2]},${dateList[0]}"
}


