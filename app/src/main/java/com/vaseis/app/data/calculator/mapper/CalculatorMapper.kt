package com.vaseis.app.data.calculator.mapper

import com.vaseis.app.data.calculator.model.RemoteCalculatorExamType
import com.vaseis.app.data.calculator.model.RemoteCalculatorGroup
import com.vaseis.app.data.calculator.model.RemoteCalculatorLesson
import com.vaseis.app.data.calculator.model.RemoteCalculatorResponse
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.domain.calculation.entities.CalculatorGroup
import com.vaseis.app.domain.calculation.entities.CalculatorLesson
import com.vaseis.app.domain.calculation.entities.CalculatorResponse
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class CalculatorMapper @Inject constructor() {

    suspend operator fun invoke(response: RemoteCalculatorResponse): CalculatorResponse {
        return CalculatorResponse(mapExamType(response.examTypes))
    }

    private suspend fun mapExamType(mappable: List<RemoteCalculatorExamType?>?): List<CalculatorExamType> {
        return mappable?.mapAsync { it?.toCalculatorExamType() ?: CalculatorExamType("", "", "", listOf()) } ?: emptyList()
    }

    private suspend fun RemoteCalculatorExamType.toCalculatorExamType(): CalculatorExamType =
        CalculatorExamType(
            examTypeItem ?: "",
            fullName ?: "",
            shortName ?: "",
            mapGroup(groups)
        )


    private suspend fun mapGroup(mappable: List<RemoteCalculatorGroup?>?): List<CalculatorGroup> {
        return mappable?.mapAsync { it?.toCalculatorGroup() ?: CalculatorGroup("", "", "", listOf()) } ?: emptyList()
    }

    private suspend fun RemoteCalculatorGroup.toCalculatorGroup(): CalculatorGroup =
        CalculatorGroup(
            groupId ?: "",
            fullName ?: "",
            shortName ?: "",
            mapLesson(lessons)
        )

    private suspend fun mapLesson(mappable: List<RemoteCalculatorLesson?>?): List<CalculatorLesson> {
        return mappable?.mapAsync {
            it?.toCalculatorLesson() ?: CalculatorLesson("", "", "", false, 0.0)
        } ?: emptyList()
    }

    private fun RemoteCalculatorLesson.toCalculatorLesson(): CalculatorLesson =
        CalculatorLesson(
            lessonId ?: "",
            fullName ?: "",
            shortName ?: "",
            isMandatory ?: false,
            weight ?: 0.0
        )
}