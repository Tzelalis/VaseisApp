package com.example.vaseisapp.ui.dashboard.topicscenter.model

import com.example.vaseisapp.domain.properties.PropertiesExamType

data class ExamTypeItem(
    var examType: PropertiesExamType,
    var isSelected: Boolean = false
)

fun PropertiesExamType.toExamTypeItem(): ExamTypeItem {
    return ExamTypeItem(this, false)
}

fun map(mappable: List<PropertiesExamType>?): List<ExamTypeItem> {
    return mappable?.map { it.toExamTypeItem() }?.toMutableList() ?: mutableListOf()
}