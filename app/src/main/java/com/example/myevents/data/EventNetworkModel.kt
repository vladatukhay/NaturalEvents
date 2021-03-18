package com.example.myevents.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EventNetworkModel(
    @field:Json(name = "events")
    var events: List<Events>
)
