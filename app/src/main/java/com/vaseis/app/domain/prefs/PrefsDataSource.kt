package com.vaseis.app.domain.prefs

import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty

interface PrefsDataSource {
    suspend fun setLanguage(lang : Language)

    suspend fun getLanguage() : Language

    suspend fun setExamType(type : PrefProperty)

    suspend fun getExamType() : PrefProperty

    suspend fun setPrefsFields(type : List<PrefProperty>)

    suspend fun getPrefsFieldsType() : List<PrefProperty>

    suspend fun setTheme(theme : Theme)

    suspend fun getTheme() : Theme

}