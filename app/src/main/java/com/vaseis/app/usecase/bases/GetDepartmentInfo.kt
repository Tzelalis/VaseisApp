package com.vaseis.app.usecase.bases

import com.vaseis.app.domain.bases.BasesDataSource
import com.vaseis.app.domain.bases.entities.DeptInfo
import javax.inject.Inject

class GetDepartmentInfo @Inject constructor(private val dataSource: BasesDataSource) {
    suspend operator fun invoke(code : String): DeptInfo {
        return dataSource.getDepartmentInfo(code)
    }
}