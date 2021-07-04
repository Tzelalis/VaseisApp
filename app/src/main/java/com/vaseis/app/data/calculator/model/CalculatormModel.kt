package com.vaseis.app.data.calculator.model

import com.squareup.moshi.Json

data class RemoteCalculatorResponse(
    @Json(name = "exam_types")val examTypes: List<RemoteCalculatorExamType>?
)

data class RemoteCalculatorExamType(
    @Json(name = "exam_type_id")val examTypeItem: String?,
    @Json(name = "exam_type_full_name")val fullName: String?,
    @Json(name = "exam_type_short_name")val shortName: String?,
    val groups: List<RemoteCalculatorGroup>?
)

data class RemoteCalculatorGroup(
    @Json(name = "group_id")val groupId: String?,
    @Json(name = "group_full_name")val fullName: String?,
    @Json(name = "short_short_name")val shortName: String?,
    val lessons: List<RemoteCalculatorLesson>?
)

data class RemoteCalculatorLesson(
    @Json(name = "lesson_id")val lessonId: String?,
    @Json(name = "full_name")val fullName: String?,
    @Json(name = "short_name")val shortName: String?,
    @Json(name = "is_mandatory")val isMandatory: Boolean?,
    val weight: Double?
)