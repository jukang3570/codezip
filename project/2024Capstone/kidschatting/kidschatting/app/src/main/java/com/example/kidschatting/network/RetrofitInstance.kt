package com.example.kidschatting.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = ""

    // OkHttpClient 설정
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(3000, TimeUnit.SECONDS) // 연결 타임아웃: 30초
        .readTimeout(3000, TimeUnit.SECONDS)    // 읽기 타임아웃: 30초
        .writeTimeout(3000, TimeUnit.SECONDS)   // 쓰기 타임아웃: 30초
        .build()

    // Retrofit 설정
    val api: ChatApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // OkHttpClient 연결
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApi::class.java)
    }



}
