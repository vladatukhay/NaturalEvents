package com.example.myevents.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Events (

    @field:Json(name = "id")
    var id: String? = "",

    @field:Json(name = "title")
    var title: String? = "",

    @field:Json(name = "link")
    var link: String? = "",

    @field:Json(name = "sources")
    val sources: List<Source>?,

//    @field:Json(name = "geometry")
//    var geometry: List<Geometry>?
    )