package com.example.vaseisapp.data.university

import com.example.vaseisapp.domain.entities.Department
import com.example.vaseisapp.utils.mapAsync
import com.squareup.moshi.Json

data class RemoteResponseDepartment(
    val records: String
)

data class RemoteDepartment(
    val code: String?,
    val name: String?,
    @Json(name = "uni-id") val uniId: String?,
    @Json(name = "title") val uniTitle: String?,
    @Json(name = "fullTitle") val uniFullTitle: String?,
    val isActive: Int?,
    @Json(name = "logoURL") val logoUrl: String?
)
