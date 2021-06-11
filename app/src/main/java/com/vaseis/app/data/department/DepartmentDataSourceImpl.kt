package com.vaseis.app.data.department

import com.vaseis.app.data.department.mapper.DepartmentListMapper
import com.vaseis.app.data.department.mapper.DepartmentMapper
import com.vaseis.app.domain.department.DepartmentDataSource
import com.vaseis.app.domain.entities.Department
import javax.inject.Inject


class DepartmentDataSourceImpl @Inject constructor(
    private val repo: DepartmentRepository,
    private val departmentListMapper: DepartmentListMapper,
    private val departmentMapper: DepartmentMapper
) : DepartmentDataSource {
    override suspend fun fetchAllDepartments(): List<Department> {
        return departmentListMapper(repo.fetchAllDepartments().sortedBy { it.code }.toMutableList())
    }

    override suspend fun fetchDepartment(code: String): Department {
        return departmentMapper(repo.fetchDepartment(code))
    }
}