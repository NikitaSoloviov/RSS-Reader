package com.dts.retrofit.presentation.model

import com.dts.retrofit.data.Item
import java.util.*

data class NewsModel(
    val title: String,
    val createAt: Date,
    val author: String,
    val data: Item
)
