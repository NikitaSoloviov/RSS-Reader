package com.dts.retrofit.data

import com.dts.retrofit.data.entity.NewsEntity
import com.dts.retrofit.domain.OnLoadNewsCallback


interface NewsDataBase {

    suspend fun fetchAll(): List<NewsEntity>

    fun fetchAll(callback: OnLoadNewsCallback)

    suspend fun saveAll(news: List<NewsEntity>)

    suspend fun getById(id: Long): NewsEntity?

    suspend fun save(news: NewsEntity)

    suspend fun deleteAll()

}

