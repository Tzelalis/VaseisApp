package com.vaseis.app.domain.properties


data class PropertiesExamType(
    val id : String,
    val fullName : String,
    val shortName : String,
    val filter : String
)

enum class ExamType(name : String){
    FILTERS("filters"), TOPICS("topics"), CALCULATOR("calculator")
}