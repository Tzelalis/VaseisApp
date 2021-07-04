package com.vaseis.app.data.prefs

interface PrefsRepository {
    suspend fun setLanguage(lang : String)

    suspend fun getLanguage() : String

    suspend fun setExamType(type : String)

    suspend fun getExamType() : String

    suspend fun setPrefsField(type : String)

    suspend fun getPrefsFields() : String

    suspend fun setTheme(theme : String)

    suspend fun getTheme() : String
}