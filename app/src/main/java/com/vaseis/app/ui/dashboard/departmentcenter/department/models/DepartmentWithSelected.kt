package com.vaseis.app.ui.dashboard.departmentcenter.department.models

import com.vaseis.app.domain.entities.Department
import com.vaseis.app.utils.mapAsync

data class DepartmentWithSelected(
    val department : Department,
    var isSelected: Boolean,
    var isNowSelected : Boolean
)

suspend fun map(mappable: MutableList<Department>?): List<DepartmentWithSelected> {
    return mappable?.mapAsync { it.toDepartmentWithSelected() } ?: emptyList()
}

fun Department.toDepartmentWithSelected() : DepartmentWithSelected {
    return DepartmentWithSelected(this, isSelected = false, isNowSelected = false)
}