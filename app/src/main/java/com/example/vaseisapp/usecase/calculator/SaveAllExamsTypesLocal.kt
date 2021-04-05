package com.example.vaseisapp.usecase.calculator

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.calculation.entities.ExamType

class SaveAllExamsTypesLocal(private val dataSource: CalculatorDataSource) {
    suspend operator fun invoke(list: List<ExamType>) {
        return dataSource.saveDataLocal(list)
    }
}