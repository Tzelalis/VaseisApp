package com.example.vaseisapp.usecase.university

import com.example.vaseisapp.domain.university.UniversityDataSource
import com.example.vaseisapp.domain.entities.University

class FetchAllUniversitiesUseCase(private val dataSource: UniversityDataSource) {
    suspend operator fun invoke(): List<University> {
        return dataSource.fetchAllUniversities()
    }
}