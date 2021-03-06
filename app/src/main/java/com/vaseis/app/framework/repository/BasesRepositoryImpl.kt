package com.vaseis.app.framework.repository

import android.content.Context
import com.vaseis.app.data.bases.BasesRepository
import com.vaseis.app.data.bases.model.RemoteDeptInfo
import com.vaseis.app.data.bases.model.RemoteResponseUniversity
import com.vaseis.app.data.bases.model.RemoteStatsDept
import com.vaseis.app.data.bases.model.toDepartmentBases
import com.vaseis.app.domain.bases.entities.DepartmentBases
import com.vaseis.app.framework.network.BasesApi
import com.vaseis.app.utils.handleExternalApiFormat
import javax.inject.Inject

class BasesRepositoryImpl @Inject constructor(
    private val api: BasesApi,
) : BasesRepository {

    override suspend fun getDepartmentBase(code: String, type: String): List<DepartmentBases> {
        val result = api.getBases(code, type).handleExternalApiFormat()

        val list = mutableListOf<DepartmentBases>()
        for (item in result)
            list.add(item.toDepartmentBases())

        return list
    }

    override suspend fun getUniversities(): RemoteResponseUniversity {
        return api.getUniversities().handleExternalApiFormat()
    }

    override suspend fun getDeptStats(code: String): RemoteStatsDept {
        return api.getDeptStats(code, "gel-gen").handleExternalApiFormat()[0]
    }

    override suspend fun getDeptInfo(code: String): RemoteDeptInfo {
        return api.getDeptInfo(code).handleExternalApiFormat()[0]
    }
}