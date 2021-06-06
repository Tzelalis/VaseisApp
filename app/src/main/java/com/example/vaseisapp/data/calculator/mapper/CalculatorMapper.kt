package com.example.vaseisapp.data.calculator.mapper

import com.example.vaseisapp.data.calculator.model.RemoteCalculatorExamType
import com.example.vaseisapp.data.calculator.model.RemoteCalculatorGroup
import com.example.vaseisapp.data.calculator.model.RemoteCalculatorLesson
import com.example.vaseisapp.data.calculator.model.RemoteCalculatorResponse
import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType
import com.example.vaseisapp.domain.calculation.entities.CalculatorGroup
import com.example.vaseisapp.domain.calculation.entities.CalculatorLesson
import com.example.vaseisapp.utils.mapAsync
import javax.inject.Inject

class CalculatorMapper @Inject constructor() {

    suspend operator fun invoke(examTypes: RemoteCalculatorResponse): List<CalculatorExamType> {
        return map(examTypes.examsTypes.toMutableList())
    }

    private suspend fun map(mappable: MutableList<RemoteCalculatorExamType?>?): List<CalculatorExamType> {
        return mappable?.map { it.toCalculatorExamType() } ?: emptyList()
    }

    private suspend fun RemoteCalculatorExamType?.toCalculatorExamType(): CalculatorExamType {
        return CalculatorExamType(
            this?.id ?: "",
            this?.fullName ?: "",
            this?.shortName ?: "",
            mapGroup(this?.groups?.toMutableList())
        )
    }

    private suspend fun mapGroup(mappable: MutableList<RemoteCalculatorGroup?>?): List<CalculatorGroup> {
        return mappable?.map { it.toCalculatorGroup() } ?: emptyList()
    }

    private suspend fun RemoteCalculatorGroup?.toCalculatorGroup(): CalculatorGroup {
        return CalculatorGroup(
            this?.id ?: "",
            this?.fullName ?: "",
            this?.shortName ?: "",
            mapLesson(this?.mandatoryLessons?.toMutableList()),
            this?.optionalCount ?: 0
        )
    }

    suspend fun mapLesson(mappable: MutableList<RemoteCalculatorLesson?>?): List<CalculatorLesson> {
        return mappable?.mapAsync { it.toCalculatorLesson() } ?: emptyList()
    }

    private fun RemoteCalculatorLesson?.toCalculatorLesson(): CalculatorLesson {
        return CalculatorLesson(
            this?.id ?: "",
            this?.fullName ?: "",
            this?.shortName ?: "",
            this?.gravity ?: 0.0,
            this?.isMandatory ?: false
        )
    }
}