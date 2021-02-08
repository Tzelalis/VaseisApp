package com.example.vaseisapp.data

import com.example.vaseisapp.data.university.RemoteUniversity
import com.example.vaseisapp.domain.datasource.UniversityDataSource

class UniversityDataSourceImpl(private val repo : UniversityRepository) : UniversityDataSource {
    override suspend fun fetchAllUniversities(): List<RemoteUniversity> {
        return repo.fetchAllUniversities()
    }
}