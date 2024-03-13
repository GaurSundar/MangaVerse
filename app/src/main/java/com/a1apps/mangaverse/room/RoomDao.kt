package com.a1apps.mangaverse.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.a1apps.mangaverse.model.Manga

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertManga(manga: List<Manga>)

    @Query("SELECT * FROM manga")
    fun getMangas(): List<Manga>
}