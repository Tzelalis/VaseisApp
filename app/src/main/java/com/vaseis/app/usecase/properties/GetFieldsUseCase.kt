package com.vaseis.app.usecase.properties

import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.domain.properties.ExamType
import com.vaseis.app.domain.properties.PropertiesDataSource
import com.vaseis.app.domain.properties.PropertiesExamType
import javax.inject.Inject

class GetFieldsUseCase @Inject constructor(private val dataSource: PropertiesDataSource) {
    suspend operator fun invoke(): List<Field> {
        return dataSource.getFields()
    }
}