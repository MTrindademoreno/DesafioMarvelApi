package com.example.marvel

import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(toByteArray())
    return digest.joinToString("") {
        String.format("%02x", it)
    }



}
fun String.FullPath(extension: String,size:String):String{
return "${this}/$size.${extension}"
}