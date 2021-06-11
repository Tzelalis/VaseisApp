package com.vaseis.app.data.department

import com.vaseis.app.data.university.RemoteDepartment

interface DepartmentRepository {
    suspend fun fetchAllDepartments() : List<RemoteDepartment>

    suspend fun fetchDepartment(code : String) : RemoteDepartment
}