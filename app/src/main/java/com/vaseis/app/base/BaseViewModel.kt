package com.vaseis.app.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaseis.app.utils.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    fun launch(shouldLoad: Boolean = true, delayValue: Long = 0, function: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                delay(delayValue)

                function.invoke()
            } catch (exception: Exception) {
                Log.e("TZEL", exception.message.toString())
            }
        }
    }

    protected fun launchWithProgress(preload: suspend () -> Unit = {}, function: suspend () -> Unit) {
        viewModelScope.launch {
            preload.invoke()
            LoadingLiveData.postValue(true)
            function.invoke()
        }.apply {
            invokeOnCompletion {
                LoadingLiveData.postValue(false)
            }
        }
    }


}

object LoadingLiveData : SingleLiveEvent<Boolean>()