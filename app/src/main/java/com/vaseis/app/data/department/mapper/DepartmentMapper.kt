package com.vaseis.app.data.department.mapper

import com.vaseis.app.data.university.RemoteDepartment
import com.vaseis.app.domain.entities.Department
import javax.inject.Inject

class DepartmentMapper @Inject constructor() {

    operator fun invoke(dept: RemoteDepartment): Department {
        return Department(
            dept.code ?: "",
            dept.name ?: "",
            dept.uniId ?: "",
            dept.uniTitle ?: "",
            dept.uniFullTitle ?: "",
            dept.isActive == 1,
            dept.logoUrl ?: "",
            dept.websiteURL ?: "",
            dept.phone ?: "",
            dept.email ?: "",
            dept.uniLogoURL ?: "",
            dept.fields?.split("/") ?: listOf()
        )
    }
}