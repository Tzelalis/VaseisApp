package com.example.vaseisapp.ui.dashboard.departmentcenter.filters.models

import com.example.vaseisapp.domain.entities.University

data class UniversityFilterItem(
    var university: University,
    var isSelected: Boolean
)