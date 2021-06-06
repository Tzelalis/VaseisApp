package com.example.vaseisapp.domain.calculation

import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType

interface CalculatorDataSource {

    suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>)

    suspend fun getAllExamsTypes(): List<CalculatorExamType>


}