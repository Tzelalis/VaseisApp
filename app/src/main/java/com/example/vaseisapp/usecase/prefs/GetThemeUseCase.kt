package com.example.vaseisapp.usecase.prefs

import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty


class GetThemeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : PrefProperty {
        return dataSource.getTheme()
    }
}


