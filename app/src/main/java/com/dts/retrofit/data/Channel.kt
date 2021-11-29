package com.dts.retrofit.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(

    @field:Element(name = "image", required = false)
    var image: Image?= null,

    @field:Element(name = "description", required = false)
    var description: String?= null,

    @field:ElementList(name = "entry", inline = true)
    var items: List<Item>?= null
)
