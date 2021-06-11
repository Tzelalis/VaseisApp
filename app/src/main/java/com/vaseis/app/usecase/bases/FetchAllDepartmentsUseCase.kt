package com.vaseis.app.usecase.bases

import com.vaseis.app.domain.department.DepartmentDataSource
import com.vaseis.app.domain.entities.Department
import javax.inject.Inject

class FetchAllDepartmentsUseCase @Inject constructor(private val dataSource: DepartmentDataSource){
    suspend operator fun invoke(): List<Department> {
        return dataSource.fetchAllDepartments()
    }
}