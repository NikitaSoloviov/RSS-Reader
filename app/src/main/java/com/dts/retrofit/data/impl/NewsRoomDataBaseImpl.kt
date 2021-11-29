package com.dts.retrofit.data.impl

import android.content.Context
import androidx.room.Room
import com.dts.retrofit.data.Item
import com.dts.retrofit.data.NewsDataBase
import com.dts.retrofit.data.entity.NewsEntity
import com.dts.retrofit.data.room.NewsAppDb
import com.dts.retrofit.domain.OnLoadNewsCallback
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class NewsRoomDataBaseImpl(context: Context) : NewsDataBase, CoroutineScope {

    private val db = Room.databaseBuilder(
        context,
        NewsAppDb::class.java,
        NewsAppDb.NAME
    ).build()

    private val dao = db.newsDao()

    override suspend fun fetchAll(): List<NewsEntity> = dao.fetchAll()

    override fun fetchAll(callback: OnLoadNewsCallback) {
        launch {
            val entity = fetchAll()
            val items = entity.map { item ->
                Item.create(item)
            }
            withContext(Dispatchers.Main) {
                callback.onLoad(items)
            }
        }
    }

    override suspend fun saveAll(news: List<NewsEntity>) = dao.insert(news)

    override suspend fun getById(id: Long): NewsEntity? = dao.fetchByID(id)

    override suspend fun save(news: NewsEntity) = dao.insert(news)

    override suspend fun deleteAll() = dao.deleteAll()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO.plus(job)
}
