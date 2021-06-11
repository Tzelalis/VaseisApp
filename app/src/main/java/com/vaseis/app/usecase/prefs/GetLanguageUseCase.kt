package com.vaseis.app.usecase.prefs

import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.domain.prefs.PrefsDataSource

class GetLanguageUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : Language {
        return dataSource.getLanguage()
    }
}

