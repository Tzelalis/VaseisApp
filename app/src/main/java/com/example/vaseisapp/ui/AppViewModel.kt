package com.example.vaseisapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.usecase.prefs.GetLanguageUseCase
import com.example.vaseisapp.utils.SingleLiveEvent
import javax.inject.Inject

class AppViewModel  @ViewModelInject constructor(private val getLanguageUseCase: GetLanguageUseCase): BaseViewModel() {
    private var _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle = _toolbarTitle

    fun setToolbarTitle(title : String) {
        _toolbarTitle.value = title
    }
}