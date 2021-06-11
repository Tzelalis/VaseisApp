package com.vaseis.app.data.calculator

import com.vaseis.app.domain.calculation.entities.CalculatorExamType

interface CalculatorRepository {

    suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>)

    suspend fun getAllExamsTypes(): List<CalculatorExamType>
}