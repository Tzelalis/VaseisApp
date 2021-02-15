package com.example.vaseisapp.data

import com.example.vaseisapp.domain.entities.Department

interface DepartmentRepository {
    suspend fun fetchAllDepartments() : List<Department>

    suspend fun fetchDepartment(code : String) : Department
}