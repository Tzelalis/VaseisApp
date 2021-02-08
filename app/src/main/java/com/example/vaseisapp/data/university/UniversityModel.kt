package com.example.vaseisapp.data.university

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json

data class RemoteResponseUniversity(
    val records : List<RemoteUniversity>
)

data class RemoteUniversity(
    val id : String?,
    val title : String?,
    @Json(name = "full-title") val fullTitle : String?
)