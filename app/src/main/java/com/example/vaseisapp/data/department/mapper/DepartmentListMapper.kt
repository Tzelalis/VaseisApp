package com.example.vaseisapp.data.department.mapper

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.entities.Department
import com.example.vaseisapp.utils.mapAsync
import javax.inject.Inject

class DepartmentListMapper @Inject constructor() {

    suspend operator fun invoke(list: List<RemoteDepartment>): List<Department> {
        return map(list.toMutableList())
    }

    fun RemoteDepartment.toDepartment(): Department {
        return Department(
            code ?: "",
            name ?: "",
            uniId ?: "",
            uniTitle ?: "",
            uniFullTitle ?: "",
            isActive == 1,
            logoUrl ?: ""
        )
    }

    suspend fun map(mappable: MutableList<RemoteDepartment>?): List<Department> {
        return mappable?.mapAsync { it.toDepartment() } ?: emptyList()
    }
}