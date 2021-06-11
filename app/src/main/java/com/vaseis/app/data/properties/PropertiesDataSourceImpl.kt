package com.vaseis.app.data.properties

import com.vaseis.app.data.properties.mapper.PropertiesExamTypeMapper
import com.vaseis.app.domain.properties.PropertiesDataSource
import com.vaseis.app.domain.properties.PropertiesExamType
import javax.inject.Inject

class PropertiesDataSourceImpl @Inject constructor(
    private val repo: PropertiesRepository,
    private val propertiesExamTypeMapper: PropertiesExamTypeMapper
) : PropertiesDataSource {
    override suspend fun getPropertiesExamType(): List<PropertiesExamType> {
        return propertiesExamTypeMapper(repo.getPropertiesExamTypes())
    }

}