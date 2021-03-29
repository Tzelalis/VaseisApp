package com.example.vaseisapp.usecase.department

import com.example.vaseisapp.domain.department.DepartmentDataSource
import com.example.vaseisapp.domain.entities.Department

class FetchDepartmentUseCase(private val dataSource: DepartmentDataSource) {

    suspend fun invoke(code : String) : Department {
        return dataSource.fetchDepartment(code)
    }
}