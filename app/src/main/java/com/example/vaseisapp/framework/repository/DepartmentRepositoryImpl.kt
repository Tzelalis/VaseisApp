package com.example.vaseisapp.framework.repository

import com.example.vaseisapp.data.DepartmentRepository
import com.example.vaseisapp.data.university.toDepartment
import com.example.vaseisapp.domain.entities.Department
import com.example.vaseisapp.framework.network.DepartmentApi
import com.example.vaseisapp.data.university.map

class DepartmentRepositoryImpl(private val api : DepartmentApi) : DepartmentRepository {
    override suspend fun fetchAllDepartments(): List<Department> {
        return map(api.fetchAllDepartments().toMutableList())
    }

    override suspend fun fetchDepartment(code: String): Department {
        return api.fetchDepartment(code).toDepartment()
    }
}