package com.vaseis.app.data.properties.model

import com.squareup.moshi.Json

data class RemoteFieldResponse(
    val fields : List<RemoteField>?
)

data class RemoteField(
    @Json(name = "field_id") val fieldId: String?,
    val key: String?,
    @Json(name = "full_name") val fullName: String?,
    @Json(name = "short_name") val shortName: String?,
    val description: String?
)