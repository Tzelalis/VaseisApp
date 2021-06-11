package com.vaseis.app.data.bases.model

data class RemoteStatsDept(
    val code: String?,
    val statistics: List<RemoteStats>?
)

data class RemoteStats(
    val candidatePreferences: RemoteCandidatePreferences?,
    val examType: String?,
    val positions: String?,
    val successfulPreferences: RemoteSuccessfulPreferences?,
    val totalCandidates: String?,
    val totalSuccessful: String?,
    val year: String?
)

data class RemoteCandidatePreferences(
    val first: Int?,
    val other: Int?,
    val second: Int?,
    val third: Int?
)

data class RemoteSuccessfulPreferences(
    val fifth: Int?,
    val first: Int?,
    val fourth: Int?,
    val other: Int?,
    val second: Int?,
    val sixth: Int?,
    val third: Int?
)