package com.example.vaseisapp.ui.dashboard.accountcenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyItem

class AccountViewModel @ViewModelInject constructor() : BaseViewModel() {
    private var _properties = MutableLiveData<List<List<PropertyItem>>>()
    val properties : LiveData<List<List<PropertyItem>>> = _properties

    fun loadProperties()    {

    }
}