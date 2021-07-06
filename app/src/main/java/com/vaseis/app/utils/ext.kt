package com.vaseis.app.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import retrofit2.Response
import java.io.IOException

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
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
fun ConstraintLayout.setTopMarginForStatusBar()  {
    val statusBarHeight = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"))
    setPadding(0, statusBarHeight , 0, 0)
}