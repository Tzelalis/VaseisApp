package com.example.vaseisapp.data.department

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.entities.Department

interface DepartmentRepository {
    suspend fun fetchAllDepartments() : List<RemoteDepartment>

    suspend fun fetchDepartment(code : String) : RemoteDepartment
}