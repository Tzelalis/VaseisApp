package com.example.vaseisapp.usecase.calculator

import com.example.vaseisapp.domain.properties.PropertiesDataSource
import com.example.vaseisapp.domain.properties.PropertiesExamType
import javax.inject.Inject

class GetPropertiesExamsTypes @Inject constructor(private val dataSource: PropertiesDataSource) {
    suspend operator fun invoke(): List<PropertiesExamType> {
        return dataSource.getPropertiesExamType()
    }
}