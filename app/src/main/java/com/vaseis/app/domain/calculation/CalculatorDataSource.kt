package com.vaseis.app.domain.calculation

import com.vaseis.app.domain.calculation.entities.CalculatorExamType

interface CalculatorDataSource {

    suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>)

    suspend fun getAllExamsTypes(): List<CalculatorExamType>


}