package com.example.vaseisapp.ui.dashboard.calculator.model

import com.example.vaseisapp.domain.calculation.entities.CalculatorLesson

data class LessonItem(
    val calculatorLesson: CalculatorLesson,
    var degree : String
)

fun CalculatorLesson.toGroupItem(): LessonItem {
    return LessonItem(this, "")
}

fun map(mappable: List<CalculatorLesson>?): List<LessonItem> {
    return mappable?.map { it.toGroupItem() }?.toMutableList() ?: mutableListOf()
}
