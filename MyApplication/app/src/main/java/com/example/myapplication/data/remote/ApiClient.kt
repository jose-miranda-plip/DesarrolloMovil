package com.example.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // Dirección base del servicio de prueba.
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Retrofit convierte las respuestas JSON a los modelos Kotlin.
    val servicio: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}