package com.example.vaseisapp.data.calculator

import com.example.vaseisapp.domain.calculation.entities.ExamType

interface CalculatorRepository {
    suspend fun fetchExamsTypes()

    suspend fun saveDataLocal(listOfExamType: List<ExamType>)

    suspend fun getAllExamsTypes(): List<ExamType>
}