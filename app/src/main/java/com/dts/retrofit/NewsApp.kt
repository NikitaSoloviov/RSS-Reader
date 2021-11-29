package com.dts.retrofit

import android.app.Application
import com.dts.retrofit.data.NewsDataBase
import com.dts.retrofit.data.impl.NewsRoomDataBaseImpl

class NewsApp : Application() {

    companion object {
        lateinit var db: NewsDataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = NewsRoomDataBaseImpl(this)
    }
}
