package com.example.marvel.home

import com.example.marvel.api.ApiServiceHq
import com.example.marvel.api.ResponseApi



class HqRepository {

    suspend fun getHq():ResponseApi {


        return try {
            val response1 = ApiServiceHq.hqApi.getHq(30,200)
            if (response1.isSuccessful) {

                ResponseApi.Success(response1.body())

            } else {
                ResponseApi.Error("Erro ao carregar os dados")

            }

        } catch (exception: Exception) {
            ResponseApi.Error("Erro ao carregar os dados")
        }
    }

}