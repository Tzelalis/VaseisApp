package com.example.vaseisapp.domain.prefs

import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty

interface PrefsDataSource {
    suspend fun setLanguage(lang : Language)

    suspend fun getLanguage() : Language

    suspend fun setExamType(type : PrefProperty)

    suspend fun getExamType() : PrefProperty

    suspend fun setGroupType(type : PrefProperty)

    suspend fun getGroupType() : PrefProperty

    suspend fun setTheme(theme : PrefProperty)

    suspend fun getTheme() : PrefProperty

}