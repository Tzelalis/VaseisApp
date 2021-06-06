package com.example.vaseisapp.domain.properties

interface PropertiesDataSource {
    suspend fun getPropertiesExamType() : List<PropertiesExamType>
}