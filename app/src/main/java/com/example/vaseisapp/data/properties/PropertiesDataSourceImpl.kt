package com.example.vaseisapp.data.properties

import com.example.vaseisapp.data.properties.mapper.PropertiesExamTypeMapper
import com.example.vaseisapp.domain.properties.PropertiesDataSource
import com.example.vaseisapp.domain.properties.PropertiesExamType
import javax.inject.Inject

class PropertiesDataSourceImpl @Inject constructor(
    private val repo: PropertiesRepository,
    private val propertiesExamTypeMapper: PropertiesExamTypeMapper
) : PropertiesDataSource {
    override suspend fun getPropertiesExamType(): List<PropertiesExamType> {
        return propertiesExamTypeMapper(repo.getPropertiesExamTypes())
    }

}