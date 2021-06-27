package com.vaseis.app.data.bases.model

import com.vaseis.app.domain.bases.entities.Base
import com.vaseis.app.domain.bases.entities.DepartmentBases
import com.vaseis.app.utils.mapAsync


data class RemoteDepartmentBases(
    val bases: List<RemoteBase>?,
    val code: String?,
    val deptName: String?,
    val uniTitle: String?,
    val uniTitleShort: String?,
    val deptLogoURL : String?,
    val uniLogoURL : String?,
    val websiteURL : String?,
    val phone : String?,
    val email : String?
)

data class RemoteBase(
    val baseFirst: Int?,
    val baseLast: Int?,
    val examType: String?,
    val positions: Int?,
    val specialCat: String?,
    val year: Int?,
    val field : String?
)


suspend fun RemoteDepartmentBases.toDepartmentBases(): DepartmentBases {
    return DepartmentBases(
        map(bases),
        code ?: "",
        deptName ?: "",
        uniTitle ?: "",
        uniTitleShort ?: "",
        deptLogoURL ?:"",
        uniLogoURL ?:"",
        websiteURL ?:"",
        phone ?:"",
        email ?:""
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
        year ?: 0,
        field ?: ""
    )
}