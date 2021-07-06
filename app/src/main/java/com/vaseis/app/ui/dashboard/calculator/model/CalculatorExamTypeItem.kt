package com.vaseis.app.ui.dashboard.calculator.model

import com.vaseis.app.domain.calculation.entities.CalculatorExamType


data class CalculatorExamTypeItem(
    val examTypeId: String,
    val fullName: String,
    val shortName: String,
    var isSelected: Boolean = false
)