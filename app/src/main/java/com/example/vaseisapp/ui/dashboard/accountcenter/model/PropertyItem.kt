package com.example.vaseisapp.ui.dashboard.accountcenter.model

data class PropertyItem(
    val id: PropertyFragment? = null,
    val title: String,
    val field: String
)

enum class PropertyFragment {
    USER_TYPE, CATEGORY, THEME, LANGUAGE, RATE_US, SHARE, BUG,
}