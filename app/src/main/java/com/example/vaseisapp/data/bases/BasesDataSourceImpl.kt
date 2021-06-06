package com.example.vaseisapp.data.bases

import com.example.vaseisapp.data.bases.mapper.UniversityMapper
import com.example.vaseisapp.domain.bases.BasesDataSource
import com.example.vaseisapp.domain.bases.entities.DepartmentBases
import com.example.vaseisapp.domain.entities.ResponseUniversity
import com.example.vaseisapp.domain.entities.University
import javax.inject.Inject

class BasesDataSourceImpl @Inject constructor(private val repo: BasesRepository, private val universityMapper: UniversityMapper) : BasesDataSource {
    override suspend fun getBases(code: String): List<DepartmentBases> {
        return repo.getDepartmentBase(code)
    }

    override suspend fun getUniversities(): ResponseUniversity {
        return universityMapper(repo.getUniversities())
    }
}