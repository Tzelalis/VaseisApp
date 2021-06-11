package com.vaseis.app.usecase.bases

import com.vaseis.app.domain.bases.BasesDataSource
import com.vaseis.app.domain.bases.entities.StatsDept
import javax.inject.Inject


class GetDeptStatsUseCase @Inject constructor(private val dataSource: BasesDataSource) {
    suspend operator fun invoke(code: String): StatsDept {
        return dataSource.getDeptStats(code)
    }
}