package com.vaseis.app.ui.dashboard.departmentcenter.filters.mappers

import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.FieldFilterItem
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class BaseFilterFieldMapper @Inject constructor() {

    suspend operator fun invoke(item: List<Field>?): List<FieldFilterItem> {
        return map(item)
    }

    suspend fun map(mappable: List<Field>?): List<FieldFilterItem> {
        return mappable?.mapAsync { FieldFilterItem(it, false) } ?: emptyList()
    }
}