package com.example.vaseisapp.ui.dashboard.accountcenter.model

import com.example.vaseisapp.R

data class PropertyItem(
    val id: PropertyFragment? = null,
    val title: String,
    val field: String
)

enum class PropertyFragment(val stringId: Int) {
    USER_TYPE(R.string.account_user_type),
    THEME(R.string.account_theme),
    LANGUAGE(R.string.account_language),
    RATE_US(R.string.account_rate),
    SHARE(R.string.account_share),
    BUG(R.string.account_bug),
    EXAM_TYPE(R.string.account_exam_type_name),
    GROUP_TYPE(R.string.account_group_type)
}