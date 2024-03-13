package com.a1apps.mangaverse.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.a1apps.mangaverse.model.Manga

@Database(entities = [Manga::class], version = 1)
abstract class MangaDBHelper : RoomDatabase() {

    abstract fun mangaDao(): RoomDao

    companion object {

        @Volatile
        private var INSTANCE: MangaDBHelper? = null

        fun getDatabase(context: Context): MangaDBHelper {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MangaDBHelper::class.java,
                    "mangaDB"
                ).build()
            }

            return INSTANCE!!
        }
    }
}