package com.vaseis.app.usecase.calculator

import com.vaseis.app.domain.properties.PropertiesDataSource
import com.vaseis.app.domain.properties.PropertiesExamType
import javax.inject.Inject

class GetPropertiesExamsTypes @Inject constructor(private val dataSource: PropertiesDataSource) {
    suspend operator fun invoke(): List<PropertiesExamType> {
        return dataSource.getPropertiesExamType()
    }
}