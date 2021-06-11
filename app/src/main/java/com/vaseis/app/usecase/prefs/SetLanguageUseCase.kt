package com.vaseis.app.usecase.prefs

import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.domain.prefs.PrefsDataSource

class SetLanguageUseCase(private val dataSource: PrefsDataSource) {
    suspend operator fun invoke(lang: Language): Any {
        return dataSource.setLanguage(lang)
    }
}