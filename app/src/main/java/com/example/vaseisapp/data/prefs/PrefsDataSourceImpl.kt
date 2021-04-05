package com.example.vaseisapp.data.prefs

import android.util.Log
import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PrefsDataSourceImpl(private val repo: PrefsRepository, private val moshi: Moshi) : PrefsDataSource {
    override suspend fun setLanguage(lang: Language) {
        repo.setLanguage(lang.code)
    }

    override suspend fun getLanguage(): Language {
        return when (repo.getLanguage()) {
            "en" -> Language.ENGLISH
            "el" -> Language.GREEK
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
        }catch (e : Exception)  {
            PrefProperty("", "")
        }
    }

    override suspend fun setGroupType(type: PrefProperty) {
        val adapter: JsonAdapter<PrefProperty> = moshi.adapter(PrefProperty::class.java)
        repo.setGroupType(adapter.toJson(type))
    }

    override suspend fun getGroupType(): PrefProperty {
        val result = repo.getGroupType()

        val adapter: JsonAdapter<PrefProperty> = moshi.adapter(PrefProperty::class.java)
        return try {
            adapter.fromJson(result) ?: PrefProperty("", "")
        }catch (e : Exception)  {
            PrefProperty("", "")
        }
    }

    override suspend fun setTheme(theme: PrefProperty) {
        val adapter: JsonAdapter<PrefProperty> = moshi.adapter(PrefProperty::class.java)
        repo.setTheme(adapter.toJson(theme))
    }

    override suspend fun getTheme(): PrefProperty {
        val result = repo.getTheme()

        val adapter: JsonAdapter<PrefProperty> = moshi.adapter(PrefProperty::class.java)
        return withContext(Dispatchers.IO) {
            adapter.fromJson(result) ?: PrefProperty("", "")
        }
    }
}