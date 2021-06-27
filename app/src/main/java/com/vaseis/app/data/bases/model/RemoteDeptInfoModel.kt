package com.vaseis.app.data.bases.model

import com.squareup.moshi.Json

data class RemoteDeptInfo(
    val code: String?,
    val email: String?,
    val isActive: Int?,
    val logoURL: String?,
    val name: String?,
    val phone: String?,
    @Json(name="uni-id")val uniId: String?,
    @Json(name="fullTitle")val uniFullName: String?,
    @Json(name="title")val uniShortName: String?,
    @Json(name = "uniLogoURL")val uniLogoUrl : String?,
    val websiteURL: String?,
    @Json(name = "uniLogoURL") val fields : String?
)