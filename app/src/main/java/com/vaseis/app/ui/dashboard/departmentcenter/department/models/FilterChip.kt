package com.vaseis.app.ui.dashboard.departmentcenter.department.models

data class FilterChip(
    val name : String,
    val type : FilterChipType,
    val id : String = ""
)

enum class FilterChipType   {
    ORDER, DISABLE_DEPARTMENTS, FIELD, UNIVERSITIES, EXAM_TYPE
}
