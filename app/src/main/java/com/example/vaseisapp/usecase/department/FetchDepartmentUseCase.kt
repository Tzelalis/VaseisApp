package com.example.vaseisapp.usecase.department

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.datasource.DepartmentDataSource

class FetchDepartmentUseCase(private val dataSource: DepartmentDataSource) {

    suspend fun invoke(code : String) : RemoteDepartment{
        return dataSource.fetchDepartment(code)
    }
}