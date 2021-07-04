package com.vaseis.app.ui.personalization.fields.model

import com.vaseis.app.domain.bases.entities.Field

data class FieldPersonalizationItem(
    val field: Field,
    var isSelected: Boolean
)