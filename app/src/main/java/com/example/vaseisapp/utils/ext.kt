package com.example.vaseisapp.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import retrofit2.Response
import java.io.IOException

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun <T : Any> Response<T>.handleExternalApiFormat(): T {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        throw IOException()
    }
}