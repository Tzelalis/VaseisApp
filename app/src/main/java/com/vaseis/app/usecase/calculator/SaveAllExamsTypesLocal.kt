package com.vaseis.app.usecase.calculator

import com.vaseis.app.domain.calculation.CalculatorDataSource
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import javax.inject.Inject

class SaveAllExamsTypesLocal @Inject constructor(private val dataSource: CalculatorDataSource) {
    suspend operator fun invoke(list: List<CalculatorExamType>) {
        return dataSource.saveDataLocal(list)
    }
}