package com.example.vaseisapp.data.department.mapper

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.entities.Department
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
            dept.logoUrl ?: ""
        )
    }
}