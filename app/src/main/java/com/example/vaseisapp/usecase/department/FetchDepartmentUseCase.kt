package com.example.vaseisapp.usecase.department

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.datasource.DepartmentDataSource
import com.example.vaseisapp.domain.entities.Department

class FetchDepartmentUseCase(private val dataSource: DepartmentDataSource) {

    suspend fun invoke(code : String) : Department {
        return dataSource.fetchDepartment(code)
    }
}