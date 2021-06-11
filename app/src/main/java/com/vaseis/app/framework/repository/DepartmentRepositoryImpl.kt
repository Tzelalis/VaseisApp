package com.vaseis.app.framework.repository

 import com.vaseis.app.data.department.DepartmentRepository
import com.vaseis.app.data.university.RemoteDepartment
import com.vaseis.app.framework.network.DepartmentApi
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor(private val api: DepartmentApi) : DepartmentRepository {
    override suspend fun fetchAllDepartments(): List<RemoteDepartment> {
        return api.fetchAllDepartments().toMutableList()
    }

    override suspend fun fetchDepartment(code: String): RemoteDepartment {
        return api.fetchDepartment(code)
    }
}