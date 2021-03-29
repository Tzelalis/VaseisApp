package com.example.vaseisapp.data.bases

import com.example.vaseisapp.domain.bases.DepartmentBases
import com.example.vaseisapp.domain.bases.BasesDataSource

class BasesDataSourceImpl(private val repo : BasesRepository) : BasesDataSource {
    override suspend fun getBases(code: String): List<DepartmentBases> {
        return repo.getDepartmentBase(code)
    }
}