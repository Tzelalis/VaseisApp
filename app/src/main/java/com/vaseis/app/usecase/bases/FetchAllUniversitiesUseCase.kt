package com.vaseis.app.usecase.bases

import com.vaseis.app.domain.bases.BasesDataSource
import com.vaseis.app.domain.entities.ResponseUniversity
import javax.inject.Inject

class FetchAllUniversitiesUseCase @Inject constructor(private val dataSource: BasesDataSource) {
    suspend operator fun invoke(): ResponseUniversity {
        return dataSource.getUniversities()
    }
}