package com.example.vaseisapp.data.bases

import com.example.vaseisapp.data.bases.model.RemoteResponseUniversity
import com.example.vaseisapp.domain.bases.entities.DepartmentBases

interface BasesRepository {
    suspend fun getDepartmentBase(code: String): List<DepartmentBases>

    suspend fun getUniversities() : RemoteResponseUniversity
}