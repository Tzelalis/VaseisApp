package com.example.vaseisapp.data.bases

import com.example.vaseisapp.domain.bases.DepartmentBases

interface BasesRepository {
    suspend fun getDepartmentBase(code : String) : List<DepartmentBases>
}