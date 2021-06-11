package com.vaseis.app.domain.bases

import com.vaseis.app.domain.bases.entities.DepartmentBases
import com.vaseis.app.domain.bases.entities.StatsDept
import com.vaseis.app.domain.entities.ResponseUniversity

interface BasesDataSource {
    suspend fun getBases(code: String): List<DepartmentBases>

    suspend fun getUniversities() : ResponseUniversity

    suspend fun getDeptStats(code : String) : StatsDept
}