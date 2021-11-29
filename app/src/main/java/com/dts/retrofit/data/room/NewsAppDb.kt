package com.dts.retrofit.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dts.retrofit.data.NewsDao
import com.dts.retrofit.data.NewsDataBase
import com.dts.retrofit.data.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class
    ],
    version = 1
)
abstract class NewsAppDb: RoomDatabase() {

    companion object {
        const val NAME = "news_database.db"
    }

    abstract fun newsDao(): NewsDao
}
