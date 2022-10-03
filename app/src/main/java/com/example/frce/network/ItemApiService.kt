package com.example.frce.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ItemApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}

object ItemApi {
    val retrofitService: ItemApiService by lazy {
        retrofit.create(ItemApiService::class.java)
    }
}