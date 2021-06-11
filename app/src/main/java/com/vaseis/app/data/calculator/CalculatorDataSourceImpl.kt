package com.vaseis.app.data.calculator

import com.vaseis.app.domain.calculation.CalculatorDataSource
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import javax.inject.Inject

class CalculatorDataSourceImpl @Inject constructor(private val repo: CalculatorRepository) : CalculatorDataSource {


    override suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>) {
        repo.saveDataLocal(listOfCalculatorExamType)
    }

    override suspend fun getAllExamsTypes(): List<CalculatorExamType> {
        return repo.getAllExamsTypes()
    }
}