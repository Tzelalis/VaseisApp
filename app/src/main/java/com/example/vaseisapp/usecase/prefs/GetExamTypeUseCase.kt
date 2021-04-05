package com.example.vaseisapp.usecase.prefs

import com.example.vaseisapp.domain.prefs.PrefsDataSource
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty

class GetExamTypeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : PrefProperty {
        return dataSource.getExamType()
    }
}