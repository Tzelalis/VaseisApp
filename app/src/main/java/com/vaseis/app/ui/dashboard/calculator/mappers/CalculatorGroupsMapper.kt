package com.vaseis.app.ui.dashboard.calculator.mappers

import com.vaseis.app.domain.calculation.entities.CalculatorGroup
import com.vaseis.app.ui.dashboard.calculator.model.GroupItem
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject


class CalculatorGroupsMapper @Inject constructor() {

    suspend operator fun invoke(list: List<CalculatorGroup>): List<GroupItem> {
        return mapExamType(list)
    }

    private suspend fun mapExamType(mappable: List<CalculatorGroup?>?): List<GroupItem> {
        return mappable?.mapAsync { it?.toCalculatorGroupItem() ?: GroupItem(CalculatorGroup("", "", "", emptyList()), false) }
            ?: emptyList()
    }

    private fun CalculatorGroup.toCalculatorGroupItem(): GroupItem = GroupItem(this, false)
}