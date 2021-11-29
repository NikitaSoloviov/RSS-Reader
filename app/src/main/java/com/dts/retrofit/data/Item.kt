package com.dts.retrofit.data

import android.os.Parcel
import android.os.Parcelable
import com.dts.retrofit.data.entity.NewsEntity
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Root(name = "item", strict = false)
data class Item(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "category", required = false)
    var category: String? = null,

    @field:Element(name = "author", required = false)
    var author: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "guid", required = false)
    var guid: String? = null,

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeString(category)
        parcel.writeString(author)
        parcel.writeString(description)
        parcel.writeString(guid)
        parcel.writeString(pubDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {

        val formatter = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss ZZZZZ", Locale.US)

        fun create(entity: NewsEntity): Item {
            return Item(
                entity.title,
                entity.link,
                entity.category,
                entity.author,
                entity.description,
                entity.guid,
                formatter.format(entity.date.toDate())
            )
        }

        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}

fun Long.toDate(): Date = Date(this)
