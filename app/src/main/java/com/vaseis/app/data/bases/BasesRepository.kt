package com.vaseis.app.data.bases

import com.vaseis.app.data.bases.model.RemoteResponseUniversity
import com.vaseis.app.data.bases.model.RemoteStatsDept
import com.vaseis.app.domain.bases.entities.DepartmentBases

interface BasesRepository {
    suspend fun getDepartmentBase(code: String): List<DepartmentBases>

    suspend fun getUniversities() : RemoteResponseUniversity

    suspend fun getDeptStats(code:String) : RemoteStatsDept
}