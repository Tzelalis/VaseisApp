package com.example.vaseisapp.domain.datasource

import com.example.vaseisapp.domain.entities.Department

interface DepartmentDataSource {
    suspend fun fetchAllDepartments() : List<Department>

    suspend fun fetchDepartment(code : String) : Department
}