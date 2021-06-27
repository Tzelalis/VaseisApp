package com.vaseis.app.usecase.bases

import com.vaseis.app.domain.bases.BasesDataSource
import com.vaseis.app.domain.bases.entities.DepartmentBases
import javax.inject.Inject

class GetDepartmentBases @Inject constructor(private val dataSource: BasesDataSource) {
    suspend operator fun invoke(code: String, type : String): List<DepartmentBases> {
        return dataSource.getBases(code, type)
    }
}