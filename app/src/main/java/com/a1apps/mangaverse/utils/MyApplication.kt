package com.a1apps.mangaverse.utils

import android.app.Application
import com.a1apps.mangaverse.api.ApiInterface
import com.a1apps.mangaverse.api.ApiUtilities
import com.a1apps.mangaverse.repository.MangaRepository
import com.a1apps.mangaverse.room.MangaDBHelper

class MyApplication: Application() {

    lateinit var mangaRepository: MangaRepository
    override fun onCreate() {
        super.onCreate()

        val database = MangaDBHelper.getDatabase(applicationContext)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        mangaRepository = MangaRepository(apiInterface, database, applicationContext)

    }
}