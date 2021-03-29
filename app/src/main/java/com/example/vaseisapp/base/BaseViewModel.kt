package com.example.vaseisapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    fun launch(shouldLoad: Boolean, delayValue: Long = 0, function: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                delay(delayValue)

                function.invoke()
            } catch (exception: Exception) {

            }
        }
    }
}