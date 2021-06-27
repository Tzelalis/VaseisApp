package com.vaseis.app.domain.bases

import com.vaseis.app.domain.bases.entities.DepartmentBases
import com.vaseis.app.domain.bases.entities.DeptInfo
import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.domain.bases.entities.StatsDept
import com.vaseis.app.domain.entities.ResponseUniversity

interface BasesDataSource {
    suspend fun getBases(code: String, type : String): List<DepartmentBases>

    suspend fun getUniversities() : ResponseUniversity

    suspend fun getDeptStats(code : String) : StatsDept

    suspend fun getDepartmentInfo(code : String) : DeptInfo
}