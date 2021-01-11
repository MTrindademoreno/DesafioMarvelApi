package com.example.marvel.api

import com.example.marvel.model.Hq
import com.example.marvel.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HqApi {
    @GET(
        "v1/public/characters/1009610/comics?" +
                "ts=1608575553&apikey=b480b28b4a4de6918ebfdfa4329e512f" +
                "&hash=3e5529d03bb44ab5195511fc99abf3fd"
    )
    suspend fun getHq(
        @Query("limit") limite: Int,
        @Query("offset") inicio: Int

    ): Response<Hq>

    @GET(
        "v1/public/comics/{comicId}?" +
                "ts=1608575553&apikey=b480b28b4a4de6918ebfdfa4329e512f" +
                "&hash=3e5529d03bb44ab5195511fc99abf3fd"
    )
    suspend fun getComic(

       @Path("comicId") comicId: Long
    ):Response<Hq>



}
