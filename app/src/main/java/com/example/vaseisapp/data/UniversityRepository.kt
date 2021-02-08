package com.example.vaseisapp.data

import com.example.vaseisapp.data.university.RemoteUniversity

interface UniversityRepository {
    suspend fun fetchAllUniversities() : List<RemoteUniversity>
}