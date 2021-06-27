package com.vaseis.app.data.department.mapper

import com.vaseis.app.data.university.RemoteDepartment
import com.vaseis.app.domain.entities.Department
import com.vaseis.app.utils.mapAsync
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
            logoUrl ?: "",
            websiteURL ?: "",
            phone ?: "",
            email ?: "",
            uniLogoURL ?: "",
            fields?.split("/") ?: listOf()
        )
    }

    suspend fun map(mappable: MutableList<RemoteDepartment>?): List<Department> {
        return mappable?.mapAsync { it.toDepartment() } ?: emptyList()
    }
}