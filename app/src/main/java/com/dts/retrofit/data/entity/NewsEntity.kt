package com.dts.retrofit.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dts.retrofit.data.Item
import java.util.*

@Entity(tableName = "news_table")
data class NewsEntity(
    val title: String,
    val link: String,
    val category: String,
    val author: String,
    val description: String,
    val guid: String,
    val date: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {

    companion object {
        fun create(item: Item): NewsEntity {
            return NewsEntity(
                item.title.orEmpty(),
                item.link.orEmpty(),
                item.category.orEmpty(),
                item.author.orEmpty(),
                item.description.orEmpty(),
                item.guid.orEmpty(),
                item.pubDate?.let { Item.formatter.parse(it) }?.time ?: Date().time
            )
        }
    }
}
