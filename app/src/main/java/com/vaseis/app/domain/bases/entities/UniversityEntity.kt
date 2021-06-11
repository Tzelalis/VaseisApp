package com.vaseis.app.domain.entities

import com.squareup.moshi.Json

data class ResponseUniversity(
    val records : List<University>
)

data class University(
    val id : String,
    val title : String,
    @Json(name = "full-title") val fullTitle : String
)