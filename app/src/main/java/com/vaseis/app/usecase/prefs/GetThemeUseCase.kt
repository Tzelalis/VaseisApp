package com.vaseis.app.usecase.prefs

import com.vaseis.app.domain.prefs.PrefsDataSource
import com.vaseis.app.domain.prefs.Theme


class GetThemeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : Theme {
        return dataSource.getTheme()
    }
}


