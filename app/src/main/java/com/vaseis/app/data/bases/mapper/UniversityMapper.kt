package com.vaseis.app.data.bases.mapper

import com.vaseis.app.data.bases.model.RemoteResponseUniversity
import com.vaseis.app.data.bases.model.RemoteUniversity
import com.vaseis.app.domain.entities.ResponseUniversity
import com.vaseis.app.domain.entities.University
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class UniversityMapper @Inject constructor() {

    suspend operator fun invoke(response: RemoteResponseUniversity): ResponseUniversity {
        return ResponseUniversity(
            map(response.records.toMutableList())
        )
    }

    private fun RemoteUniversity.toUniversity(): University {
        return University(
            id ?: "",
            title ?: "",
            fullTitle ?: ""
        )
    }

    private suspend fun map(mappable: MutableList<RemoteUniversity>?): List<University> {
        return mappable?.mapAsync { it.toUniversity() } ?: emptyList()
    }
}