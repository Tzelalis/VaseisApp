package com.example.vaseisapp.framework.repository

import com.example.vaseisapp.data.university.UniversityRepository
import com.example.vaseisapp.data.university.toResponseUniversity
import com.example.vaseisapp.domain.entities.University
import com.example.vaseisapp.framework.network.UniversityApi

class UniversityRepositoryImpl(private val api : UniversityApi) : UniversityRepository {
    override suspend fun fetchAllUniversities(): List<University> {
        val response = api.getUniversities()

        if(response.isSuccessful && response.body() != null)
            return response.body()?.toResponseUniversity()?.records ?: emptyList()

        return response.body()!!.toResponseUniversity().records //todo throw exception
    }
}