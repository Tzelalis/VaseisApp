package com.example.vaseisapp.data.bases.mapper

import com.example.vaseisapp.domain.entities.University
import com.example.vaseisapp.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.example.vaseisapp.utils.mapAsync
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