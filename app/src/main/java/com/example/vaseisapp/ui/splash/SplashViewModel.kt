package com.example.vaseisapp.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel

class SplashViewModel @ViewModelInject constructor() : BaseViewModel(){
    private var _showMainUi = MutableLiveData<Boolean>()
    val showMainUi = _showMainUi

    fun loadStartData() {
        launch(true, 0){
            _showMainUi.value = true
        }

    }
}