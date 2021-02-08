package com.example.vaseisapp.domain.datasource

import com.example.vaseisapp.data.university.RemoteUniversity

interface UniversityDataSource {
    suspend fun fetchAllUniversities() : List<RemoteUniversity>
}