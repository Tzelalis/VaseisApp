package com.example.vaseisapp.framework.repository

import com.example.vaseisapp.data.bases.BasesRepository
import com.example.vaseisapp.data.university.toDepartmentBases
import com.example.vaseisapp.domain.bases.DepartmentBases
import com.example.vaseisapp.framework.network.BasesApi
import com.example.vaseisapp.utils.handleExternalApiFormat

class BasesRepositoryImpl(private val api : BasesApi) : BasesRepository {

    override suspend fun getDepartmentBase(code: String): List<DepartmentBases> {
        val result = api.getBases(code).handleExternalApiFormat()

        val list = mutableListOf<DepartmentBases>()
        for(item in result)
            list.add(item.toDepartmentBases())

        return list
    }
}