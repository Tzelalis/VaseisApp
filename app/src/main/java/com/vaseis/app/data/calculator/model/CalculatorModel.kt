package com.vaseis.app.data.calculator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteCalculatorResponse(
    var examsTypes: List<RemoteCalculatorExamType>
) : Parcelable

@Parcelize
data class RemoteCalculatorExamType(
    var id: String,
    var fullName: String,
    var shortName: String,
    var groups: List<RemoteCalculatorGroup>,
) : Parcelable

@Parcelize
data class RemoteCalculatorGroup(
    var id: String,
    var fullName: String,
    var shortName: String,
    var mandatoryLessons: List<RemoteCalculatorLesson>,
    var optionalCount: Int,
) : Parcelable

@Parcelize
data class RemoteCalculatorLesson(
    var id: String = "",
    var fullName: String,
    var shortName: String,
    var gravity: Double,
    var isMandatory: Boolean,
) : Parcelable



