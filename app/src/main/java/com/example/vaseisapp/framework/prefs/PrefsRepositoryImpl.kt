package com.example.vaseisapp.framework.prefs

import android.content.SharedPreferences
import com.example.vaseisapp.data.prefs.PrefsRepository
import com.example.vaseisapp.utils.EXAMS_TYPE_PREFS
import com.example.vaseisapp.utils.GROUP_TYPE_PREFS
import com.example.vaseisapp.utils.LANGUAGE_PREFS
import com.example.vaseisapp.utils.THEME_PREFS

class PrefsRepositoryImpl(private val prefs: SharedPreferences) : PrefsRepository {
    override suspend fun setLanguage(lang: String) {
        prefs.edit().putString(LANGUAGE_PREFS, lang).apply()
    }

    override suspend fun getLanguage(): String {
        return prefs.getString(LANGUAGE_PREFS, "") ?: ""
    }

    override suspend fun setExamType(type: String) {
        prefs.edit().putString(EXAMS_TYPE_PREFS, type).apply()
    }

    override suspend fun getExamType(): String {
        return prefs.getString(EXAMS_TYPE_PREFS, "") ?: ""
    }

    override suspend fun setGroupType(type: String) {
        prefs.edit().putString(GROUP_TYPE_PREFS, type).apply()
    }

    override suspend fun getGroupType(): String {
        return prefs.getString(GROUP_TYPE_PREFS, "") ?: ""
    }

    override suspend fun setTheme(theme: String) {
        prefs.edit().putString(THEME_PREFS, theme).apply()
    }

    override suspend fun getTheme(): String {
        return prefs.getString(THEME_PREFS, "") ?: ""
    }
}