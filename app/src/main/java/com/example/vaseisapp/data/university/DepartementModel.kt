package com.example.vaseisapp.data.university

import androidx.recyclerview.widget.DiffUtil
import com.example.vaseisapp.domain.entities.Department
import com.example.vaseisapp.domain.entities.University
import com.example.vaseisapp.utils.mapAsync
import com.squareup.moshi.Json

data class RemoteResponseDepartment(
    val records : String
)

data class RemoteDepartment(
    val code : String,
    val name : String?,
    @Json(name = "uni-id") val uniId : String?,
    @Json(name = "title") val uniTitle : String?,
    @Json(name = "fullTitle")val uniFullTitle : String?
)


fun RemoteDepartment.toDepartment() : Department {
    return Department(
        code ?: "",
        name ?: "",
        uniId ?: "",
        uniTitle ?: "",
        uniFullTitle ?: ""
    )
}

suspend fun map(mappable: MutableList<RemoteDepartment>?): List<Department> {
    return mappable?.mapAsync { it.toDepartment() } ?: emptyList()
}