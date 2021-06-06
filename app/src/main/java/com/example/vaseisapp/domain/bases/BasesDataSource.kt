package com.example.vaseisapp.domain.bases

import com.example.vaseisapp.domain.bases.entities.DepartmentBases
import com.example.vaseisapp.domain.entities.ResponseUniversity
import com.example.vaseisapp.domain.entities.University

interface BasesDataSource {
    suspend fun getBases(code: String): List<DepartmentBases>

    suspend fun getUniversities() : ResponseUniversity
}