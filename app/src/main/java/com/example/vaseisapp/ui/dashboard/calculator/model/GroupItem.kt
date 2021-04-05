package com.example.vaseisapp.ui.dashboard.calculator.model

import com.example.vaseisapp.domain.calculation.entities.Group

data class GroupItem(
    val group: Group,
    var isSelected: Boolean
)

fun Group.toGroupItem(): GroupItem {
    return GroupItem(this, false)
}

fun map(mappable: List<Group>?): List<GroupItem> {
    return mappable?.map { it.toGroupItem() }?.toMutableList() ?: mutableListOf()
}
