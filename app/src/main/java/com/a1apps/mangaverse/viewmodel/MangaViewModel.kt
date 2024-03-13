package com.a1apps.mangaverse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a1apps.mangaverse.model.MangaResponse
import com.a1apps.mangaverse.repository.MangaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MangaViewModel(private val mangaRepository: MangaRepository) : ViewModel() {

    private val PAGE_NO = 1
    val mangas: LiveData<MangaResponse>
        get() = mangaRepository.manga

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mangaRepository.getMangas(PAGE_NO)
        }
    }


    private val _searchResults = MutableLiveData<MangaResponse>()
    val searchResults: LiveData<MangaResponse>
        get() = _searchResults

    fun searchManga(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResponse = mangaRepository.searchManga(query)
            _searchResults.postValue(searchResponse)
        }
    }
}