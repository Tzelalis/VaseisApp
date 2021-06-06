package com.example.vaseisapp.ui.dashboard.departmentcenter.department.models

import com.example.vaseisapp.domain.entities.Department
import com.example.vaseisapp.utils.mapAsync

data class DepartmentWithSelected(
    val code: String,
    val name: String,
    val uniId: String,
    val uniTitle : String,
    val uniFullTitle : String,
    val isActive : Boolean,
    val logoUrl : String,
    var isSelected: Boolean,
    var isNowSelected : Boolean
)

suspend fun map(mappable: MutableList<Department>?): List<DepartmentWithSelected> {
    return mappable?.mapAsync { it.toDepartmentWithSelected() } ?: emptyList()
}

fun Department.toDepartmentWithSelected() : DepartmentWithSelected {
    return DepartmentWithSelected(code, name, uniId, uniTitle, uniFullTitle, isActive, logoUrl, isSelected = false, isNowSelected = false)
}