package com.example.vaseisapp.usecase.prefs

import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.domain.prefs.Theme


class GetThemeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : Theme {
        return dataSource.getTheme()
    }
}


