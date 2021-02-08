package com.example.vaseisapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {
    val progressIndicatorUi = MutableLiveData<Boolean>()
    //val genericErrorUi = MutableLiveData<BaseApiException>()

    fun launch(shouldLoad: Boolean, delayValue: Long = 0, function: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                delay(delayValue)
                //progressIndicatorUi.value = shouldLoad
                function.invoke()
            } catch (exception: Exception) {
                //genericErrorUi.value = BaseApiException(NETWORK_ERROR_CODE)
            }

            /*if (shouldLoad) {
                progressIndicatorUi.value = false
            }*/
        }
    }
}