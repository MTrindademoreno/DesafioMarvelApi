package com.example.marvel.home

import com.example.marvel.api.ApiServiceHq
import com.example.marvel.api.ResponseApi

class DetailRepository {
suspend fun  getComic(id:Long):ResponseApi{
        return try {
            val response = ApiServiceHq.hqApi.getComic(id)
            if (response.isSuccessful) {

                ResponseApi.Success(response.body())

            } else {
                ResponseApi.Error("Erro ao carregar os dados")

            }

        } catch (exception: Exception) {
            ResponseApi.Error("Erro ao carregar os dados")
        }
}

}
