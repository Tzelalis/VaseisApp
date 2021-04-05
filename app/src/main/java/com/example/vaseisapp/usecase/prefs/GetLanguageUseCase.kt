package com.example.vaseisapp.usecase.prefs

import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.domain.prefs.PrefsDataSource

class GetLanguageUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : Language {
        return dataSource.getLanguage()
    }
}

