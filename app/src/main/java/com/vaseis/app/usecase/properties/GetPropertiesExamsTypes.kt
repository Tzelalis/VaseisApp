package com.vaseis.app.usecase.calculator

import com.vaseis.app.domain.properties.ExamType
import com.vaseis.app.domain.properties.PropertiesDataSource
import com.vaseis.app.domain.properties.PropertiesExamType
import javax.inject.Inject

class GetPropertiesExamsTypes @Inject constructor(private val dataSource: PropertiesDataSource) {
    suspend operator fun invoke(type: ExamType): List<PropertiesExamType> {
        return dataSource.getPropertiesExamType(type.name.toLowerCase())
    }
}