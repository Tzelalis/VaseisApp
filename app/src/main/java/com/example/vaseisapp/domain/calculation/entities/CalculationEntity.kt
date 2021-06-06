package com.example.vaseisapp.domain.calculation.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CalculatorResponse(
    var examsTypeCalculators : List<CalculatorExamType>
) : Parcelable

@Parcelize
data class CalculatorExamType(
    var id: String,
    var name: String,
    var short_name: String,
    var calculatorGroups: List<CalculatorGroup>,
) : Parcelable

@Parcelize
data class CalculatorGroup(
    var id: String,
    var fullName: String,
    var shortName: String,
    var mandatoryCalculatorLessons: List<CalculatorLesson>,
    var optionalCount: Int,
) : Parcelable

@Parcelize
data class CalculatorLesson(
    var id: String = "0",
    var fullName: String,
    var shortName: String,
    var gravity: Double,
    var isMandatory: Boolean,
) : Parcelable