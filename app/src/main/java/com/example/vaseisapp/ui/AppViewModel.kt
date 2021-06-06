package com.example.vaseisapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.prefs.Theme
import com.example.vaseisapp.usecase.prefs.GetLanguageUseCase
import com.example.vaseisapp.usecase.prefs.GetThemeUseCase
import kotlinx.coroutines.launch

class AppViewModel @ViewModelInject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : BaseViewModel() {
    private var _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle = _toolbarTitle

    private var _themeUI = MutableLiveData<Theme>()
    val themeUI : LiveData<Theme> = _themeUI


    fun setToolbarTitle(title: String) {
        _toolbarTitle.value = title
    }

    fun loadTheme()  {
        launch(true){
            _themeUI.value = getThemeUseCase() ?: Theme.SYSTEM_DEFAULT
        }
    }
}