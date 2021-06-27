package com.vaseis.app.domain.properties

import com.vaseis.app.domain.bases.entities.Field

interface PropertiesDataSource {
    suspend fun getPropertiesExamType(type : String) : List<PropertiesExamType>

    suspend fun getFields() : List<Field>
}