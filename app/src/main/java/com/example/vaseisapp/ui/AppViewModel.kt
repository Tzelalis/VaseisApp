package com.example.vaseisapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.utils.SingleLiveEvent

class AppViewModel @ViewModelInject constructor() : BaseViewModel() {
    private var _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle = _toolbarTitle

    fun setToolbarTitle(title : String) {
        _toolbarTitle.value = title
    }
}