package com.vaseis.app.framework.prefs

import android.content.SharedPreferences
import com.vaseis.app.data.prefs.PrefsRepository
import com.vaseis.app.utils.EXAMS_TYPE_PREFS
import com.vaseis.app.utils.GROUP_TYPE_PREFS
import com.vaseis.app.utils.LANGUAGE_PREFS
import com.vaseis.app.utils.THEME_PREFS

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