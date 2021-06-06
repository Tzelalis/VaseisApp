package com.example.vaseisapp.usecase.calculator

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType
import javax.inject.Inject

class SaveAllExamsTypesLocal @Inject constructor(private val dataSource: CalculatorDataSource) {
    suspend operator fun invoke(list: List<CalculatorExamType>) {
        return dataSource.saveDataLocal(list)
    }
}