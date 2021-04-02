package com.example.vaseisapp.domain.calculation.entities

data class CalculationEntity(
    val examTypes : List<ExamType>,
)

data class ExamType(
    val id : String,
    val name : String,
    val short_name : String,
    val groups : List<Group>
)

data class Group(
    val fullName: String,
    val shortName : String,
    val mandatoryLessons : List<Lesson>,
    val optionalLessons : List<Lesson>,
    val optionalCount : Int
)

data class Lesson(
    val id : String,
    val fullName : String,
    val shortName : String,
    val gravity : Double
)