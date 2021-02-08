package com.example.vaseisapp.data

import com.example.vaseisapp.data.university.RemoteDepartment

interface DepartmentRepository {
    suspend fun fetchAllDepartments() : List<RemoteDepartment>

    suspend fun fetchDepartment(code : String) : RemoteDepartment
}