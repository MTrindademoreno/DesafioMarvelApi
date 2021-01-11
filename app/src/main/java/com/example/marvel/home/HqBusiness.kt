package com.example.marvel.home

import com.example.marvel.api.ResponseApi
import com.example.marvel.model.Hq
import com.example.marvel.model.Result

class HqBusiness {
    private val repository by lazy {
        HqRepository()
    }

    suspend fun getHq(): ResponseApi {


        return repository.getHq()
    }
}