package com.example.vaseisapp.data.bases.mapper

import com.example.vaseisapp.data.bases.model.RemoteResponseUniversity
import com.example.vaseisapp.data.bases.model.RemoteUniversity
import com.example.vaseisapp.domain.entities.ResponseUniversity
import com.example.vaseisapp.domain.entities.University
import com.example.vaseisapp.utils.mapAsync
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