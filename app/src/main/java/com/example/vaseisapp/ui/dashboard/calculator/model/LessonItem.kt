package com.example.vaseisapp.ui.dashboard.calculator.model

import com.example.vaseisapp.domain.calculation.entities.Lesson

data class LessonItem(
    val lesson: Lesson,
    var degree : String
)

fun Lesson.toGroupItem(): LessonItem {
    return LessonItem(this, "")
}

fun map(mappable: List<Lesson>?): List<LessonItem> {
    return mappable?.map { it.toGroupItem() }?.toMutableList() ?: mutableListOf()
}
