package com.example.vaseisapp.usecase.calculator

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.calculation.entities.ExamType

class GetAllExamsTypesUseCase(private val dataSource: CalculatorDataSource) {
    suspend operator fun invoke(): List<ExamType> {
        return dataSource.getAllExamsTypes()
    }
}