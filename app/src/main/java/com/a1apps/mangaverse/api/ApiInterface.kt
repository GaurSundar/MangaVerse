package com.a1apps.mangaverse.api

import com.a1apps.mangaverse.model.MangaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "X-RapidAPI-Key: ${ApiUtilities.API_KEY}",
        "X-RapidAPI-Host: ${ApiUtilities.API_HOST}"
    )
    @GET("fetch")
    suspend fun getMangas(
        @Query("page") page: Int
    ): Response<MangaResponse>


    @Headers(
        "X-RapidAPI-Key: ${ApiUtilities.API_KEY}",
        "X-RapidAPI-Host: ${ApiUtilities.API_HOST}"
    )
    @GET("search")
    suspend fun searchManga(
        @Query("text") text: String
    ): Response<MangaResponse>
}