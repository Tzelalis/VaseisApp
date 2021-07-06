package com.vaseis.app.ui.dashboard.calculator.mappers

import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.ui.dashboard.calculator.model.CalculatorExamTypeItem
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class CalculatorLessonsMapper @Inject constructor() {

    suspend operator fun invoke(list: List<CalculatorExamType>): List<CalculatorExamTypeItem> {
        return mapExamType(list)
    }

    private suspend fun mapExamType(mappable: List<CalculatorExamType?>?): List<CalculatorExamTypeItem> {
        return mappable?.mapAsync { it?.toCalculatorExamTypeItem() ?: CalculatorExamTypeItem("", "", "") } ?: emptyList()
    }

    private fun CalculatorExamType.toCalculatorExamTypeItem(): CalculatorExamTypeItem = CalculatorExamTypeItem(examTypeId, fullName, shortName)
}