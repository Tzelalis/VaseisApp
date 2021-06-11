package com.vaseis.app.domain.prefs

import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty

interface PrefsDataSource {
    suspend fun setLanguage(lang : Language)

    suspend fun getLanguage() : Language

    suspend fun setExamType(type : PrefProperty)

    suspend fun getExamType() : PrefProperty

    suspend fun setGroupType(type : PrefProperty)

    suspend fun getGroupType() : PrefProperty

    suspend fun setTheme(theme : Theme)

    suspend fun getTheme() : Theme

}