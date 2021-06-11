package com.vaseis.app.ui.dashboard.departmentcenter.department.models

import com.vaseis.app.domain.entities.Department
import com.vaseis.app.utils.mapAsync

data class DepartmentWithSelected(
    val code: String,
    val name: String,
    val uniId: String,
    val uniTitle : String,
    val uniFullTitle : String,
    val isActive : Boolean,
    val logoUrl : String,
    val uniUrl : String,
    var isSelected: Boolean,
    var isNowSelected : Boolean
)

suspend fun map(mappable: MutableList<Department>?): List<DepartmentWithSelected> {
    return mappable?.mapAsync { it.toDepartmentWithSelected() } ?: emptyList()
}

fun Department.toDepartmentWithSelected() : DepartmentWithSelected {
    return DepartmentWithSelected(code, name, uniId, uniTitle, uniFullTitle, isActive, logoUrl, uniLogoURL, isSelected = false, isNowSelected = false)
}