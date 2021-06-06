package com.example.vaseisapp.framework.repository

 import com.example.vaseisapp.data.department.DepartmentRepository
import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.framework.network.DepartmentApi
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor(private val api: DepartmentApi) : DepartmentRepository {
    override suspend fun fetchAllDepartments(): List<RemoteDepartment> {
        return api.fetchAllDepartments().toMutableList()
    }

    override suspend fun fetchDepartment(code: String): RemoteDepartment {
        return api.fetchDepartment(code)
    }
}