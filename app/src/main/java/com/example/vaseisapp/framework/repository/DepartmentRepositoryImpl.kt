package com.example.vaseisapp.framework.repository

import com.example.vaseisapp.data.DepartmentRepository
import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.framework.network.DepartmentApi

class DepartmentRepositoryImpl(private val api : DepartmentApi) : DepartmentRepository {
    override suspend fun fetchAllDepartments(): List<RemoteDepartment> {
        return api.fetchAllDepartments()
    }

    override suspend fun fetchDepartment(code : String): RemoteDepartment {
        return api.fetchDepartment(code)
    }
}