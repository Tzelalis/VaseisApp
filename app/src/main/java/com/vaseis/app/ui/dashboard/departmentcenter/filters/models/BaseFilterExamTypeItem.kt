package com.vaseis.app.ui.dashboard.departmentcenter.filters.models

import com.vaseis.app.domain.properties.PropertiesExamType

data class BaseFilterExamTypeItem(
    val examType: PropertiesExamType,
    var isSelected: Boolean
)