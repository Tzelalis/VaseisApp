package com.vaseis.app.ui.dashboard.departmentcenter.filters.models

import com.vaseis.app.domain.bases.entities.Field

data class FieldFilterItem(
    val field: Field,
    var isSelected: Boolean
)
