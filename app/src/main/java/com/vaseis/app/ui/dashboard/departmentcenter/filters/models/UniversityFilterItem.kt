package com.vaseis.app.ui.dashboard.departmentcenter.filters.models

import com.vaseis.app.domain.entities.University

data class UniversityFilterItem(
    var university: University,
    var isSelected: Boolean
)