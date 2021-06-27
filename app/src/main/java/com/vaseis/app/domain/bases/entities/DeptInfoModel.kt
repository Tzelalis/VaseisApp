package com.vaseis.app.domain.bases.entities

import com.squareup.moshi.Json

data class DeptInfo(
    val code: String,
    val email: String,
    val isActive: Boolean,
    val logoURL: String,
    val name: String,
    val phone: String,
    val uniId: String,
    val uniFullName : String,
    val uniShortName : String,
    val uniLogoUrl : String,
    val websiteURL: String,
    val fields : List<String>
)