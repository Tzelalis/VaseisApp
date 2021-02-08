package com.example.vaseisapp.data.university

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json

data class RemoteResponseDepartment(
    val records : String
)

data class RemoteDepartment(
    val code : Long,
    val name : String?,
    @Json(name = "uni-id") val uniId : String?,
    @Json(name = "title") val uniTitle : String?,
    @Json(name = "fullTitle")val uniFullTitle : String?
)