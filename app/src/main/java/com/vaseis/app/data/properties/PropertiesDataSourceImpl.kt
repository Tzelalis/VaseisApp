package com.vaseis.app.data.properties

import com.vaseis.app.data.properties.mapper.FieldsMapper
import com.vaseis.app.data.properties.mapper.PropertiesExamTypeMapper
import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.domain.properties.PropertiesDataSource
import com.vaseis.app.domain.properties.PropertiesExamType
import javax.inject.Inject

class PropertiesDataSourceImpl @Inject constructor(
    private val repo: PropertiesRepository,
    private val propertiesExamTypeMapper: PropertiesExamTypeMapper,
    private val fieldsMapper : FieldsMapper
) : PropertiesDataSource {
    override suspend fun getPropertiesExamType(type : String): List<PropertiesExamType> {
        return propertiesExamTypeMapper(repo.getPropertiesExamTypes(type))
    }

    override suspend fun getFields(): List<Field> {
        return fieldsMapper(repo.getFields())
    }
}