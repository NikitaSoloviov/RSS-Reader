package com.dts.retrofit.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class Image(

    @field:Element( name = "url", required = false)
    var url : String?= null,

    @field:Element(name = "title", required = false)
    var title: String?= null,

    @field:Element(name = "link", required = false)
    var link: String?= null
)
