package com.dts.retrofit.domain

import com.dts.retrofit.data.Item

interface OnLoadNewsCallback {

    fun onLoad(items: List<Item>)

    fun onFail(message: String)
}
