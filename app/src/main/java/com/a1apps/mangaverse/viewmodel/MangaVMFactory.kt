package com.a1apps.mangaverse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a1apps.mangaverse.repository.MangaRepository

class MangaVMFactory(private val mangaRepository: MangaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MangaViewModel(mangaRepository) as T
    }
}