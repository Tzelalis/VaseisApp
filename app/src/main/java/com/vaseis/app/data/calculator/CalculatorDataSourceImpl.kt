package com.vaseis.app.data.calculator

import com.vaseis.app.data.calculator.mapper.CalculatorMapper
import com.vaseis.app.domain.calculation.CalculatorDataSource
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import javax.inject.Inject

class CalculatorDataSourceImpl @Inject constructor(
    private val repo: CalculatorRepository,
    private val calculatorMapper: CalculatorMapper
) : CalculatorDataSource {


    override suspend fun saveDataLocal(listOfDummyCalculatorExamType: List<CalculatorExamType>) {
        repo.saveDataLocal(listOfDummyCalculatorExamType)
    }

    override suspend fun getCalculator(): List<CalculatorExamType> {
        val response = repo.getAllExamsTypes()
        return calculatorMapper(response).examTypes
    }
}