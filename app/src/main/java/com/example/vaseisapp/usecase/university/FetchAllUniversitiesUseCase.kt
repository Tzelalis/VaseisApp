package com.example.vaseisapp.usecase.university

import com.example.vaseisapp.data.university.RemoteUniversity
import com.example.vaseisapp.domain.datasource.UniversityDataSource

class FetchAllUniversitiesUseCase(private val dataSource: UniversityDataSource) {
    suspend operator fun invoke(): List<RemoteUniversity> {
        return dataSource.fetchAllUniversities()
    }
}