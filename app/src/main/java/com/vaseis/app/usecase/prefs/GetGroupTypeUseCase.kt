package com.vaseis.app.usecase.prefs

import com.vaseis.app.domain.prefs.PrefsDataSource
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty

class GetGroupTypeUseCase (private val dataSource: PrefsDataSource) {
    suspend operator fun invoke() : PrefProperty {
        return dataSource.getGroupType()
    }
}