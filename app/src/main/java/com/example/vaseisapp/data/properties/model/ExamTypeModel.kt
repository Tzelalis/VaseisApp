package com.example.vaseisapp.data.properties.model

import com.squareup.moshi.Json

data class RemotePropertiesExamTypeResponse(
    @Json(name = "exam_types")val examTypes : List<RemotePropertiesExamType>?
)

data class RemotePropertiesExamType(
    @Json(name = "exam_type_id")val id : String?,
    @Json(name = "full_name")val fullName : String?,
    @Json(name = "short_name")val shortName : String?
)