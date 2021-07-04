package com.vaseis.app.domain.calculation

import com.vaseis.app.domain.calculation.entities.CalculatorExamType

interface CalculatorDataSource {

    suspend fun saveDataLocal(listOfDummyCalculatorExamType: List<CalculatorExamType>)

    suspend fun getCalculator(): List<CalculatorExamType>

}