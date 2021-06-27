package com.vaseis.app.domain.bases.entities

class BasesResponse : ArrayList<DepartmentBases>()

data class DepartmentBases(
    val bases: List<Base>,
    val code: String,
    val deptName: String,
    val uniTitle: String,
    val uniTitleShort: String,
    val deptLogoURL : String,
    val uniLogoURL : String,
    val websiteURL : String,
    val phone : String,
    val email : String
)

data class Base(
    val baseFirst: Int,
    val baseLast: Int,
    val examType: String,
    val positions: Int,
    val specialCat: String,
    val year: Int,
    val field : String
)

enum class SpecialType {

}