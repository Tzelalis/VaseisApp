package com.example.vaseisapp.data.calculator

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType
import javax.inject.Inject

class CalculatorDataSourceImpl @Inject constructor(private val repo: CalculatorRepository) : CalculatorDataSource {


    override suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>) {
        repo.saveDataLocal(listOfCalculatorExamType)
    }

    override suspend fun getAllExamsTypes(): List<CalculatorExamType> {
        return repo.getAllExamsTypes()
    }
}