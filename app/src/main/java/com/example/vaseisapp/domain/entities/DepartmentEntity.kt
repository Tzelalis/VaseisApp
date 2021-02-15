package com.example.vaseisapp.domain.entities

import com.squareup.moshi.Json

data class RemoteResponseDepartment(
    val records : String
)

data class Department(
    val code : String,
    val name : String,
    @Json(name = "uni-id") val uniId : String,
    @Json(name = "title") val uniTitle : String,
    @Json(name = "fullTitle")val uniFullTitle : String
)