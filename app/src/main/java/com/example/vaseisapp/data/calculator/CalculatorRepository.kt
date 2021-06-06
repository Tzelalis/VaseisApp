package com.example.vaseisapp.data.calculator

import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType

interface CalculatorRepository {

    suspend fun saveDataLocal(listOfCalculatorExamType: List<CalculatorExamType>)

    suspend fun getAllExamsTypes(): List<CalculatorExamType>
}