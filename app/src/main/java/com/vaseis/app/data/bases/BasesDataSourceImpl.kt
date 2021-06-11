package com.vaseis.app.data.bases

import com.vaseis.app.data.bases.mapper.StatsMapper
import com.vaseis.app.data.bases.mapper.UniversityMapper
import com.vaseis.app.domain.bases.BasesDataSource
import com.vaseis.app.domain.bases.entities.DepartmentBases
import com.vaseis.app.domain.bases.entities.StatsDept
import com.vaseis.app.domain.entities.ResponseUniversity
import javax.inject.Inject

class BasesDataSourceImpl @Inject constructor(
    private val repo: BasesRepository,
    private val universityMapper: UniversityMapper,
    private val statsMapper: StatsMapper
) : BasesDataSource {
    override suspend fun getBases(code: String): List<DepartmentBases> {
        return repo.getDepartmentBase(code)
    }

    override suspend fun getUniversities(): ResponseUniversity {
        return universityMapper(repo.getUniversities())
    }

    override suspend fun getDeptStats(code: String): StatsDept {
        return statsMapper(repo.getDeptStats(code))
    }
}