package com.example.vaseisapp.usecase.prefs

import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.domain.prefs.PrefsDataSource

class SetLanguageUseCase(private val dataSource: PrefsDataSource) {
    suspend operator fun invoke(lang: Language): Any {
        return dataSource.setLanguage(lang)
    }
}