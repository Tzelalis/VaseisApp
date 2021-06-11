package com.vaseis.app.data.bases.mapper

import com.vaseis.app.data.bases.model.RemoteCandidatePreferences
import com.vaseis.app.data.bases.model.RemoteStats
import com.vaseis.app.data.bases.model.RemoteStatsDept
import com.vaseis.app.data.bases.model.RemoteSuccessfulPreferences
import com.vaseis.app.domain.bases.entities.CandidatePreferences
import com.vaseis.app.domain.bases.entities.Stats
import com.vaseis.app.domain.bases.entities.StatsDept
import com.vaseis.app.domain.bases.entities.SuccessfulPreferences
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class StatsMapper @Inject constructor() {

    suspend operator fun invoke(item: RemoteStatsDept): StatsDept {
        return StatsDept(
            item.code ?: "",
            map(item.statistics?.toMutableList())
        )
    }

    private suspend fun map(mappable: MutableList<RemoteStats>?): List<Stats> {
        return mappable?.mapAsync { it.toStats() } ?: emptyList()
    }

    private fun RemoteStats.toStats(): Stats {
        return Stats(
            candidatePreferences?.toCandidatePreferences() ?: CandidatePreferences(0, 0, 0, 0),
            examType ?: "",
            positions ?: "",
            successfulPreferences?.toSuccessFulPreferences()
                ?: SuccessfulPreferences(0, 0, 0, 0, 0, 0, 0),
            totalCandidates ?: "",
            totalSuccessful ?: "",
            year ?: ""
        )
    }

    private fun RemoteSuccessfulPreferences.toSuccessFulPreferences(): SuccessfulPreferences {
        return SuccessfulPreferences(
            fifth ?: 0,
            first ?: 0,
            fourth ?: 0,
            other ?: 0,
            second ?: 0,
            sixth ?: 0,
            third ?: 0
        )
    }

    private fun RemoteCandidatePreferences.toCandidatePreferences(): CandidatePreferences {
        return CandidatePreferences(
            first ?: 0,
            other ?: 0,
            second ?: 0,
            third ?: 0
        )
    }
}