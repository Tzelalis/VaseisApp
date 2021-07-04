package com.vaseis.app.data.calculator

import com.vaseis.app.data.calculator.model.RemoteCalculatorResponse
import com.vaseis.app.domain.calculation.entities.CalculatorExamType

interface CalculatorRepository {

    suspend fun saveDataLocal(listOfDummyCalculatorExamType: List<CalculatorExamType>)

    suspend fun getAllExamsTypes(): RemoteCalculatorResponse
}