package com.example.vaseisapp.data.bases.model

import com.example.vaseisapp.domain.entities.ResponseUniversity
import com.example.vaseisapp.domain.entities.University
import com.example.vaseisapp.utils.mapAsync
import com.squareup.moshi.Json

data class RemoteResponseUniversity(
    val records: List<RemoteUniversity>
)

data class RemoteUniversity(
    val id: String?,
    val title: String?,
    @Json(name = "full-title") val fullTitle: String?
)


suspend fun RemoteResponseUniversity.toResponseUniversity(): ResponseUniversity {
    return ResponseUniversity(
        map(this.records.toMutableList())
    )
}

fun RemoteUniversity.toUniversity(): University {
    return University(
        id ?: "",
        title ?: "",
        fullTitle ?: ""
    )
}

suspend fun map(mappable: MutableList<RemoteUniversity>?): List<University> {
    return mappable?.mapAsync { it.toUniversity() } ?: emptyList()
}