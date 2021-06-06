package com.example.vaseisapp.usecase.bases

import com.example.vaseisapp.domain.bases.BasesDataSource
import com.example.vaseisapp.domain.bases.entities.DepartmentBases
import javax.inject.Inject

class GetDepartmentBases @Inject constructor(private val dataSource: BasesDataSource) {
    suspend operator fun invoke(code: String): List<DepartmentBases> {
        return dataSource.getBases(code)
    }
}