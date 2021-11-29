package com.dts.retrofit.presentation.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.dts.retrofit.NewsApp
import com.dts.retrofit.data.Item
import com.dts.retrofit.domain.ApiClient
import com.dts.retrofit.domain.OnLoadNewsCallback
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsRepository(context: Context) : CoroutineScope {

    val networkManager = context.getSystemService(ConnectivityManager::class.java)
    val connectTester: Boolean = networkManager?.activeNetwork != null && networkManager.activeNetworkInfo?.isConnected == true

    fun getNews(path: String, source: String, callback: OnLoadNewsCallback) {

        launch {

            val items = if (connectTester) {
                ApiClient().loadNews(source, path)
            } else {
                NewsApp.db.fetchAll().map {
                    Item.create(it)
                }
            }
            withContext(Dispatchers.Main) {
                callback.onLoad(items)
            }
        }
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO.plus(job)
}
