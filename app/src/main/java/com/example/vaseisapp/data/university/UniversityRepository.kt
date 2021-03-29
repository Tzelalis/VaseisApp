package com.example.vaseisapp.data.university

import com.example.vaseisapp.domain.entities.University

interface UniversityRepository {
    suspend fun fetchAllUniversities() : List<University>
}