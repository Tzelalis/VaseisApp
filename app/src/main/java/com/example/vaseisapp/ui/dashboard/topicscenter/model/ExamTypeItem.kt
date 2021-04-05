package com.example.vaseisapp.ui.dashboard.topicscenter.model

import com.example.vaseisapp.domain.calculation.entities.ExamType

data class ExamTypeItem(
    var examType: ExamType,
    var isSelected: Boolean = false
)

fun ExamType.toExamTypeItem(): ExamTypeItem {
    return ExamTypeItem(this, false)
}

fun map(mappable: List<ExamType>?): List<ExamTypeItem> {
    return mappable?.map { it.toExamTypeItem() }?.toMutableList() ?: mutableListOf()
}