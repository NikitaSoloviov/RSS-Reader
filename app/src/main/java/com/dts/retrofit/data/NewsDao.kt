package com.dts.retrofit.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dts.retrofit.data.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: List<NewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: NewsEntity)

    @Query(value = "SELECT * FROM news_table")
    suspend fun fetchAll(): List<NewsEntity>

    @Query("SELECT * FROM news_table WHERE id = :id")
    suspend fun fetchByID(id: Long): NewsEntity?

    @Query("DELETE FROM news_table")
    suspend fun deleteAll()

}
