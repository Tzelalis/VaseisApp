package com.example.vaseisapp.domain.calculation

import com.example.vaseisapp.domain.calculation.entities.ExamType

interface CalculatorDataSource {
    suspend fun fetchExamsTypes()

    suspend fun saveDataLocal(listOfExamType: List<ExamType>)

    suspend fun getAllExamsTypes(): List<ExamType>


}