package com.dts.retrofit.domain

import com.dts.retrofit.NewsApp
import com.dts.retrofit.data.Item
import com.dts.retrofit.data.entity.NewsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import kotlin.coroutines.CoroutineContext

class ApiClient() : CoroutineScope {

    fun loadNews(source: String, path: String): List<Item> {
        val retrofit = createRetrofit(source)

        val api = retrofit.create(UPApiService::class.java)
        val call = api.getNews(path)
        val response = call.execute()
        return if (response.isSuccessful) {

            val channel = response.body()?.channel
            if (channel != null) {
                val items = channel.items.orEmpty()
                launch {
                    NewsApp.db.saveAll(items.map { item ->
                        NewsEntity.create(item)
                    })
                }
                items
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    private fun createRetrofit(source: String): Retrofit {
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
        //Wed, 29 Sep 2021 09:08:38 +0300
        return Retrofit.Builder()
            .baseUrl(source)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(okhttpClient)
            .build()
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO.plus(job)
}
