package com.example.vaseisapp.ui.dashboard.calculator.model

import com.example.vaseisapp.domain.calculation.entities.CalculatorGroup

data class GroupItem(
    val calculatorGroup: CalculatorGroup,
    var isSelected: Boolean
)

fun CalculatorGroup.toGroupItem(): GroupItem {
    return GroupItem(this, false)
}

fun map(mappable: List<CalculatorGroup>?): List<GroupItem> {
    return mappable?.map { it.toGroupItem() }?.toMutableList() ?: mutableListOf()
}
