package com.dts.retrofit.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "rss")
data class RssMainData(
    @field:Element(name = "channel", required = false)
    var channel: Channel? = null
)
