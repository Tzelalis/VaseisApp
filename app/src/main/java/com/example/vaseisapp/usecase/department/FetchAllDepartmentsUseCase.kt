package com.example.vaseisapp.usecase.department

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.data.university.RemoteUniversity
import com.example.vaseisapp.domain.datasource.DepartmentDataSource

class FetchAllDepartmentsUseCase(private val dataSource: DepartmentDataSource){
    suspend operator fun invoke(): List<RemoteDepartment> {
        return dataSource.fetchAllDepartments()
    }
}