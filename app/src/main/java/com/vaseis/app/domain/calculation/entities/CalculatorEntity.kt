package com.vaseis.app.domain.calculation.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class CalculatorResponse(
    val examTypeDummies: List<CalculatorExamType>
)

data class CalculatorExamType(
    val examTypeId: String,
    val fullName: String,
    val shortName: String,
    val groups: List<CalculatorGroup>
)

@Parcelize
data class CalculatorGroup(
    val groupId: String,
    val fullName: String,
    val shortName: String,
    val lessons: List<CalculatorLesson>
) : Parcelable


@Parcelize
data class CalculatorLesson(
    val lessonId: String,
    val fullName: String,
    val shortName: String,
    val isMandatory: Boolean,
    val weight: Double
) : Parcelable