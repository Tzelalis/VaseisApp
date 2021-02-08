package com.example.vaseisapp.data

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.domain.datasource.DepartmentDataSource
import java.util.*

class DepartmentDataSourceImpl(private val repo: DepartmentRepository) : DepartmentDataSource{
    override suspend fun fetchAllDepartments(): List<RemoteDepartment> {

        return repo.fetchAllDepartments().sortedBy { it.code }
    }

    override suspend fun fetchDepartment(code: String): RemoteDepartment {
        return repo.fetchDepartment(code)
    }
}