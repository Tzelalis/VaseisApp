package com.example.vaseisapp.framework.repository

import com.example.vaseisapp.data.UniversityRepository
import com.example.vaseisapp.data.university.RemoteUniversity
import com.example.vaseisapp.framework.network.UniversityApi

class UniversityRepositoryImpl(private val api : UniversityApi) : UniversityRepository {
    override suspend fun fetchAllUniversities(): List<RemoteUniversity> {
        val response = api.getUniversities()

        if(response.isSuccessful && response.body() != null)
            return response.body()!!.records

        return response.body()!!.records
    }
}