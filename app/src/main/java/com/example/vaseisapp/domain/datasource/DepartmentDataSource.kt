package com.example.vaseisapp.domain.datasource

import com.example.vaseisapp.data.university.RemoteDepartment

interface DepartmentDataSource {
    suspend fun fetchAllDepartments() : List<RemoteDepartment>

    suspend fun fetchDepartment(code : String) : RemoteDepartment
}