package com.vaseis.app.ui.dashboard.accountcenter.model

import com.vaseis.app.R

data class PropertyItem(
    val id: PropertyFragment? = null,
    val title: String,
    val field: String
)

enum class PropertyFragment(val stringId: Int) {
    THEME(R.string.account_theme),
    LANGUAGE(R.string.account_language),
    RATE_US(R.string.account_rate),
    SHARE(R.string.account_share),
    BUG(R.string.account_bug),
    EXAM_TYPE(R.string.account_exam_type_name),
    FIELDS(R.string.account_fields),
    ABOUT_API(R.string.account_about_api)
}