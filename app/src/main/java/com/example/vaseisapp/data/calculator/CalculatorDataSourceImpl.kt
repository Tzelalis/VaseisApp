package com.example.vaseisapp.data.calculator

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.calculation.entities.ExamType

class CalculatorDataSourceImpl(private val repo: CalculatorRepository) : CalculatorDataSource {
    override suspend fun fetchExamsTypes() {

    }

    override suspend fun saveDataLocal(listOfExamType: List<ExamType>) {
        repo.saveDataLocal(listOfExamType)
    }

    override suspend fun getAllExamsTypes(): List<ExamType> {
        return repo.getAllExamsTypes()
    }
}