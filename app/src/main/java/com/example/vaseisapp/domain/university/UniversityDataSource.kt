package com.example.vaseisapp.domain.university

import com.example.vaseisapp.domain.entities.University

interface UniversityDataSource {
    suspend fun fetchAllUniversities() : List<University>
}