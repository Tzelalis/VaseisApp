package com.vaseis.app.domain.bases.entities

import com.squareup.moshi.Json

data class Field(
    val fieldId: String,
    val key: String,
    val fullName: String,
    val shortName: String,
    val description: String
)