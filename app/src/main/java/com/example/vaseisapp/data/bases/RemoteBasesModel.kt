package com.example.vaseisapp.data.university

import com.example.vaseisapp.domain.bases.Base
import com.example.vaseisapp.domain.bases.DepartmentBases
import com.example.vaseisapp.utils.mapAsync


data class RemoteDepartmentBases(
    val bases: List<RemoteBase>?,
    val code: String?,
    val deptName: String?,
    val uniTitle: String?,
    val uniTitleShort: String?
)

data class RemoteBase(
    val baseFirst: Int?,
    val baseLast: Int?,
    val examType: String?,
    val positions: Int?,
    val specialCat: String?,
    val year: Int?
)



suspend fun RemoteDepartmentBases.toDepartmentBases() : DepartmentBases  {
    return DepartmentBases(
        map(bases),
        code ?:"",
        deptName ?:"",
        uniTitle ?:"",
        uniTitleShort ?:""
    )
}

suspend fun map(mappable: List<RemoteBase>?): List<Base> {
    return mappable?.mapAsync { it.toBase() } ?: emptyList()
}

fun RemoteBase.toBase(): Base {
    return Base(
        baseFirst ?: 0,
        baseLast ?: 0,
        examType ?: "",
        positions ?: 0,
        specialCat ?: "",
        year ?: 0
    )
}