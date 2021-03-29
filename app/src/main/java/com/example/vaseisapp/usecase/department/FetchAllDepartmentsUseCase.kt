package com.example.vaseisapp.usecase.department

import com.example.vaseisapp.domain.department.DepartmentDataSource
import com.example.vaseisapp.domain.entities.Department

class FetchAllDepartmentsUseCase(private val dataSource: DepartmentDataSource){
    suspend operator fun invoke(): List<Department> {
        return dataSource.fetchAllDepartments()
    }
}