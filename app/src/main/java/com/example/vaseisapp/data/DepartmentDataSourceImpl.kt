package com.example.vaseisapp.data

import com.example.vaseisapp.domain.datasource.DepartmentDataSource
import com.example.vaseisapp.domain.entities.Department

class DepartmentDataSourceImpl(private val repo: DepartmentRepository) : DepartmentDataSource{
    override suspend fun fetchAllDepartments(): List<Department> {

        return repo.fetchAllDepartments().sortedBy { it.code }
    }

    override suspend fun fetchDepartment(code: String): Department {
        return repo.fetchDepartment(code)
    }
}