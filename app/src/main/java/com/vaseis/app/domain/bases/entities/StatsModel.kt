package com.vaseis.app.domain.bases.entities

data class StatsDept(
    val code: String,
    val statistics: List<Stats>
)

data class Stats(
    val candidatePreferences: CandidatePreferences,
    val examType: String,
    val positions: String,
    val successfulPreferences: SuccessfulPreferences,
    val totalCandidates: String,
    val totalSuccessful: String,
    val year: String
)

data class CandidatePreferences(
    val first: Int,
    val other: Int,
    val second: Int,
    val third: Int
)

data class SuccessfulPreferences(
    val fifth: Int,
    val first: Int,
    val fourth: Int,
    val other: Int,
    val second: Int,
    val sixth: Int,
    val third: Int
)

fun List<Stats>.getMaxSuccessPositions(): Float {
    var max = 0
    for (stat in this) {
        if (stat.successfulPreferences.first > max)
            max = stat.successfulPreferences.first
        if (stat.successfulPreferences.second > max)
            max = stat.successfulPreferences.second
        if (stat.successfulPreferences.third > max)
            max = stat.successfulPreferences.third
        if (stat.successfulPreferences.fourth > max)
            max = stat.successfulPreferences.fourth
        if (stat.successfulPreferences.fifth > max)
            max = stat.successfulPreferences.fifth
        if (stat.successfulPreferences.sixth > max)
            max = stat.successfulPreferences.sixth
    }
    return max.toFloat()
}

fun List<Stats>.getMaxCandidatesPositions(): Float {
    var max = 0
    for (stat in this) {
        if (stat.candidatePreferences.first > max)
            max = stat.candidatePreferences.first
        if (stat.candidatePreferences.second > max)
            max = stat.candidatePreferences.second
        if (stat.candidatePreferences.third > max)
            max = stat.candidatePreferences.third
    }
    return max.toFloat()
}

fun SuccessfulPreferences.getTotal(): Int {
    return first + second + third + fourth + fifth + sixth + other
}

fun CandidatePreferences.getFirstThreePrefs(): Int {
    return first + second + third
}

fun CandidatePreferences.getTotal(): Int {
    return first + second + third + other
}