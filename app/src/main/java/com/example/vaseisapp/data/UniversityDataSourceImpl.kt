package com.example.vaseisapp.data

import com.example.vaseisapp.domain.datasource.UniversityDataSource
import com.example.vaseisapp.domain.entities.University

class UniversityDataSourceImpl(private val repo : UniversityRepository) : UniversityDataSource {
    override suspend fun fetchAllUniversities(): List<University> {
        return repo.fetchAllUniversities()
    }
}