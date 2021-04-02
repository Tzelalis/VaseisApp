package com.example.vaseisapp.ui.dashboard.calculator.model

import com.example.vaseisapp.domain.calculation.entities.ExamType

data class ExamTypeItem(
    var examType : ExamType,
    var isSelected : Boolean
)