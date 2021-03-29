package com.example.vaseisapp.domain.bases

class BasesResponse : ArrayList<DepartmentBases>()

data class DepartmentBases(
    val bases: List<Base>,
    val code: String,
    val deptName: String,
    val uniTitle: String,
    val uniTitleShort: String
)

data class Base(
    val baseFirst: Int,
    val baseLast: Int,
    val examType: String,
    val positions: Int,
    val specialCat: String,
    val year: Int
)

enum class SpecialType {

}