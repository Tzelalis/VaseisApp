package com.example.vaseisapp.usecase.bases

import com.example.vaseisapp.domain.department.DepartmentDataSource
import com.example.vaseisapp.domain.entities.Department
import javax.inject.Inject

class FetchAllDepartmentsUseCase @Inject constructor(private val dataSource: DepartmentDataSource){
    suspend operator fun invoke(): List<Department> {
        return dataSource.fetchAllDepartments()
    }
}