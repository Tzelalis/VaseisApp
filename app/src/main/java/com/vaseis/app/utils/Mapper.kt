package com.vaseis.app.utils

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <T, R> Iterable<T>.mapAsync(block: (T) -> R): List<R> = coroutineScope {
    map { async { block(it) } }.awaitAll()
}