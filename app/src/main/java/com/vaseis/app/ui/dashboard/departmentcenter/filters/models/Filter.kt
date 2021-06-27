package com.vaseis.app.ui.dashboard.departmentcenter.filters.models

import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.domain.properties.PropertiesExamType

data class Filter(
    var showDisabledDepartments : Boolean = false,
    var order : OrderType = OrderType.ALPHABETICAL,
    var examType : PropertiesExamType = PropertiesExamType("1", "", "ΓΕΛ", "gel-ime-gen"),
    var universities : List<String> = listOf(),
    var fields : MutableList<Field> = mutableListOf()
)

enum class OrderType(val title : String) { ALPHABETICAL("Αλφαβητικά"), UNIVERSITY("Βάση Ιδρύματος") }