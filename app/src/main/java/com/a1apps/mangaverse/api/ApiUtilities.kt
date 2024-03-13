package com.a1apps.mangaverse.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    private const val BASE_URL = "https://mangaverse-api.p.rapidapi.com/manga/"
    const val API_KEY = "136fd4a6e1mshd2d643ca6351bf6p181396jsn7e52c2f39941"
    const val API_HOST = "mangaverse-api.p.rapidapi.com"

    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}