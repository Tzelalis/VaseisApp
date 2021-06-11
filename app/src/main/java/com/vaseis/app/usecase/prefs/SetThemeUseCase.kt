package com.vaseis.app.usecase.prefs

import com.vaseis.app.domain.prefs.PrefsDataSource
import com.vaseis.app.domain.prefs.Theme

class SetThemeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke(theme : Theme) : Any {
        return dataSource.setTheme(theme)
    }
}

