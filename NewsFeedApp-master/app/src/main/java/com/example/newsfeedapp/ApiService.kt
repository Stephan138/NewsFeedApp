package com.example.newsfeedapp

import com.example.newsfeedapp.model.QueryResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL: String = "https://ws-tszh-1c-test.vdgb-soft.ru/api/mobile/news/list/"

interface ApiService {
    @GET(BASE_URL)
    suspend fun getInfo(): QueryResult
}

fun getQueryResult(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}