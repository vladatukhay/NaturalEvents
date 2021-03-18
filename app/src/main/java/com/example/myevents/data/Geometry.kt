package com.example.myevents.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geometry (

    @field:Json(name = "coordinates")
    val coordinates: List<Double>
)