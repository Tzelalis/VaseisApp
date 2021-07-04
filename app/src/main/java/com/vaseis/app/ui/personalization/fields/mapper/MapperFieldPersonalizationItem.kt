package com.vaseis.app.ui.personalization.fields.mapper

import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.FieldFilterItem
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class MapperFieldPersonalizationItem @Inject constructor() {

    suspend operator fun invoke(item: List<Field>?): List<FieldFilterItem> {
        return map(item)
    }

    suspend fun map(mappable: List<Field>?): List<FieldFilterItem> {
        return mappable?.mapAsync { FieldFilterItem(it, false) } ?: emptyList()
    }
}