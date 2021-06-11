package com.vaseis.app.data.topics.model

import com.squareup.moshi.Json

data class RemoteTopicResponse(
    val data: List<RemoteTopicLesson>?
)

data class RemoteTopicLesson(
    @Json(name = "lesson_id") val lessonId: String?,
    @Json(name = "full_name") val fullName: String?,
    @Json(name = "short_name") val shortName: String?,
    @Json(name = "is_mandatory")val isMandatory: Boolean,
    val weight: Int,
    val topics: List<RemoteTopic>?
)

data class RemoteTopic(
    @Json(name = "pdf_url") val pdfUrl: String?,
    @Json(name = "img_url") val imgUrl: String?,
    val year: String?
)

