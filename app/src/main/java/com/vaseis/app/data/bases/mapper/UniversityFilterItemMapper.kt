package com.vaseis.app.data.bases.mapper

import com.vaseis.app.domain.entities.University
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class UniversityFilterItemMapper @Inject constructor() {

    suspend operator fun invoke(list: List<University>): List<UniversityFilterItem> {
        return map(list.toMutableList())
    }

    private fun University.toUniversityFilterItem(): UniversityFilterItem {
        return UniversityFilterItem(this, false)
    }

    private suspend fun map(mappable: MutableList<University>?): List<UniversityFilterItem> {
        return mappable?.mapAsync { it.toUniversityFilterItem() } ?: emptyList()
    }
}