package com.example.vaseisapp.usecase.bases

import com.example.vaseisapp.domain.bases.BasesDataSource
import com.example.vaseisapp.domain.entities.ResponseUniversity
import javax.inject.Inject

class FetchAllUniversitiesUseCase @Inject constructor(private val dataSource: BasesDataSource) {
    suspend operator fun invoke(): ResponseUniversity {
        return dataSource.getUniversities()
    }
}