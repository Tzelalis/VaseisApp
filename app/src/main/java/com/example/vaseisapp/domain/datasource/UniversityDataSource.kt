package com.example.vaseisapp.domain.datasource

import com.example.vaseisapp.domain.entities.University

interface UniversityDataSource {
    suspend fun fetchAllUniversities() : List<University>
}