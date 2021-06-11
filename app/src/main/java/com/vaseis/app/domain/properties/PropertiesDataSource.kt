package com.vaseis.app.domain.properties

interface PropertiesDataSource {
    suspend fun getPropertiesExamType() : List<PropertiesExamType>
}