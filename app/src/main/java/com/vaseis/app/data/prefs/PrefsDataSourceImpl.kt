package com.vaseis.app.data.prefs

import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.domain.prefs.PrefsDataSource
import com.vaseis.app.domain.prefs.Theme
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class PrefsDataSourceImpl(private val repo: PrefsRepository, private val moshi: Moshi) : PrefsDataSource {
    override suspend fun setLanguage(lang: Language) {
        repo.setLanguage(lang.code)
    }

    override suspend fun getLanguage(): Language {
        return when (repo.getLanguage()) {
            Language.ENGLISH.code -> Language.ENGLISH
            Language.GREEK.code -> Language.GREEK
            else -> Language.SYSTEM_DEFAULT
        }
    }

    override suspend fun setExamType(type: PrefProperty) {
        val adapter: JsonAdapter<PrefProperty> = moshi.adapter(PrefProperty::class.java)
        repo.setExamType(adapter.toJson(type))
    }

    override suspend fun getExamType(): PrefProperty {
        val result = repo.getExamType()

        val adapter: JsonAdapter<PrefProperty> = moshi.adapter(PrefProperty::class.java)
        return try {
            adapter.fromJson(result) ?: PrefProperty("", "")
        } catch (e: Exception) {
            PrefProperty("", "")
        }
    }

    override suspend fun setPrefsFields(type: List<PrefProperty>) {
        val listMyData = Types.newParameterizedType(List::class.java, PrefProperty::class.java)
        val adapter: JsonAdapter<List<PrefProperty>> = moshi.adapter(listMyData)
        repo.setPrefsField(adapter.toJson(type))
    }

    override suspend fun getPrefsFieldsType(): List<PrefProperty> {
        val result = repo.getPrefsFields()

        val listMyData = Types.newParameterizedType(List::class.java, PrefProperty::class.java)
        val adapter: JsonAdapter<List<PrefProperty>> = moshi.adapter(listMyData)
        return try {
            adapter.fromJson(result) ?: listOf()
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun setTheme(theme: Theme ) {
        repo.setTheme(theme.code)
    }

    override suspend fun getTheme(): Theme {
        return when(repo.getTheme())    {
            Theme.DARK.code -> Theme.DARK
            Theme.LIGHT.code -> Theme.LIGHT
            else -> Theme.SYSTEM_DEFAULT
        }
    }
}