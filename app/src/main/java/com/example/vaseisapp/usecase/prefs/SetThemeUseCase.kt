package com.example.vaseisapp.usecase.prefs

import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.domain.prefs.Theme
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty

class SetThemeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke(theme : Theme) : Any {
        return dataSource.setTheme(theme)
    }
}

