package com.vaseis.app.ui.dashboard.departmentcenter.filters.mappers

import com.vaseis.app.domain.properties.PropertiesExamType
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.BaseFilterExamTypeItem
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject


class BaseFilterExamTypeItemMapper @Inject constructor() {

    suspend operator fun invoke(examTypes: List<PropertiesExamType>): List<BaseFilterExamTypeItem> {
        return map(examTypes.toMutableList())
    }

    suspend fun map(mappable: MutableList<PropertiesExamType>?): List<BaseFilterExamTypeItem> {
        return mappable?.mapAsync { BaseFilterExamTypeItem(it, false) } ?: emptyList()
    }
}