package com.example.maniadbmusic

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class ApiResponse @JvmOverloads constructor(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    val channel: Channel
)

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:ElementList(entry = "item", inline = true)
    @param:ElementList(entry = "item", inline = true)
    val artists: List<XmlArtist>
)

@Root(name = "item", strict = false)
data class XmlArtist @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String
)

