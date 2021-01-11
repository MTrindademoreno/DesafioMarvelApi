package com.example.marvel.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceHq {

    val hqApi = getHqComic().create(HqApi::class.java)
    fun getHqComic(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .client(getInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}



    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return interceptor.build()
    }
//
//    object ApiServiceHq {
//        private val interceptor = Interceptor { chain ->
//            val ts = "1608575553"
//            val hash = "3e5529d03bb44ab5195511fc99abf3fd"
//
//            val url = chain.request().url.newBuilder()
//                .addQueryParameter(
//                    MarvelApi.API_NAME_KEY,
//                    API_KEY
//                )
//                .addQueryParameter(TS, ts)
//                .addQueryParameter(
//                    HASH,
//                    "${ts}${HASH}".md5()
//                )
//                .build()
//            val request = chain.request()
//                .newBuilder()
//                .url(url)
//                .build()
//            chain.proceed(request)
//        }
//
//        private val logging = run {
//            val httpLoggingInterceptor = HttpLoggingInterceptor()
//            httpLoggingInterceptor.apply {
//                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            }
//        }
//
//        private val apiClient =
//            OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(logging).build()
//
//        private fun getRetrofit(): Retrofit {
//            return Retrofit.Builder().client(apiClient)
//                .baseUrl("https://gateway.marvel.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//
//
//    }
//
//
//
//    object MarvelApi {
//
//        const val API_KEY = "b480b28b4a4de6918ebfdfa4329e512f"
//        const val API_NAME_KEY ="apikey"
//        const val HASH ="hash"
//        const val TS ="ts"
//    }
//}