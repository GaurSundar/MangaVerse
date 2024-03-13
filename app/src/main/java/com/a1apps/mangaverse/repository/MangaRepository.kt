package com.a1apps.mangaverse.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a1apps.mangaverse.R
import com.a1apps.mangaverse.api.ApiInterface
import com.a1apps.mangaverse.model.MangaResponse
import com.a1apps.mangaverse.room.MangaDBHelper
import com.a1apps.mangaverse.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MangaRepository(
    private val apiInterface: ApiInterface,
    private val mangaDatabase: MangaDBHelper,
    private val applicationContext: Context
) {

    private val mangaLiveData = MutableLiveData<MangaResponse>()
    val manga: LiveData<MangaResponse>
        get() = mangaLiveData

    suspend fun getMangas(page: Int) {

        if (NetworkUtils.isInternetConnected(applicationContext)) {
            val result = apiInterface.getMangas(page)
            if (result.isSuccessful && result.body() != null) {

                mangaDatabase.mangaDao().insertManga(result.body()!!.data)
                mangaLiveData.postValue(result.body())

            }
        } else {

            val mangas = mangaDatabase.mangaDao().getMangas()
            val mangaList = MangaResponse(200, mangas)

            mangaLiveData.postValue(mangaList)

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    applicationContext.getString(R.string.no_internet),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private val _searchResults = MutableLiveData<MangaResponse>()
    val searchResults: LiveData<MangaResponse>
        get() = _searchResults

    suspend fun searchManga(query: String): MangaResponse {

        if (NetworkUtils.isInternetConnected(applicationContext)) {
            val result = apiInterface.searchManga(query)

            if (result.isSuccessful && result.body() != null) {
                return result.body()!!
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    "Please make sure you have Internet Connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return MangaResponse(404, emptyList())
    }
}