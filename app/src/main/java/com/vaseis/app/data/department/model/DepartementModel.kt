package com.vaseis.app.data.university

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
    @Json(name = "logoURL") val logoUrl: String?,
    val websiteURL : String?,
    val phone : String?,
    val email : String?,
    val uniLogoURL : String?,
    @Json(name = "field") val fields : String?
)
