package com.example.vaseisapp.usecase.calculator

import com.example.vaseisapp.domain.calculation.CalculatorDataSource
import com.example.vaseisapp.domain.calculation.entities.ExamType

class FetchAllExamsTypes (private val dataSource: CalculatorDataSource){
    suspend operator fun invoke() {
        return dataSource.fetchExamsTypes()
    }
}