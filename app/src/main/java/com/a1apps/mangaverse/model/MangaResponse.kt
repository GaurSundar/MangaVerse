package com.a1apps.mangaverse.model

data class MangaResponse(
    val code: Int,
    val `data`: List<Manga>
)