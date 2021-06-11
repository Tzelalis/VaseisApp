package com.vaseis.app.usecase.prefs

import com.vaseis.app.domain.prefs.PrefsDataSource
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty


class SetGroupTypeUseCase(private val dataSource: PrefsDataSource) {
    suspend operator fun invoke(type: PrefProperty): Any {
        return dataSource.setGroupType(type)
    }
}