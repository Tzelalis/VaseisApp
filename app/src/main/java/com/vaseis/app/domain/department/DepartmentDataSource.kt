package com.vaseis.app.domain.department

import com.vaseis.app.domain.entities.Department

interface DepartmentDataSource {
    suspend fun fetchAllDepartments() : List<Department>

    suspend fun fetchDepartment(code : String) : Department
}