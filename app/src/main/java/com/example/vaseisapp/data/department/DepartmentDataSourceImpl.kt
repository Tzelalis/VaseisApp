package com.example.vaseisapp.data.department

import com.example.vaseisapp.domain.department.DepartmentDataSource
import com.example.vaseisapp.domain.entities.Department

class DepartmentDataSourceImpl(private val repo: DepartmentRepository) : DepartmentDataSource {
    override suspend fun fetchAllDepartments(): List<Department> {

        return repo.fetchAllDepartments().sortedBy { it.code }
    }

    override suspend fun fetchDepartment(code: String): Department {
        return repo.fetchDepartment(code)
    }
}