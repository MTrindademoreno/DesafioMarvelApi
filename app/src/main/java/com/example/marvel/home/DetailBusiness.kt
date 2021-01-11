package com.example.marvel.home

import com.example.marvel.api.ResponseApi

class DetailBusiness {
    private val mrepository by lazy {
        DetailRepository()
    }
    suspend fun getComic(id: Long):ResponseApi{
       return mrepository.getComic(id)
    }
}