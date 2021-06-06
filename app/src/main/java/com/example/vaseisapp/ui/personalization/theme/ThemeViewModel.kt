package com.example.vaseisapp.ui.personalization.theme

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.prefs.Theme
import com.example.vaseisapp.usecase.prefs.GetThemeUseCase
import com.example.vaseisapp.usecase.prefs.SetThemeUseCase

class ThemeViewModel @ViewModelInject constructor(
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : BaseViewModel() {

    private var _themeUI = MutableLiveData<Theme>()
    val themeUI: LiveData<Theme> = _themeUI

    private var _savedUI = MutableLiveData<Theme>()
    val savedUI: LiveData<Theme> = _savedUI

    fun loadPrefTheme() {
        launch(true) {
            _themeUI.value = getThemeUseCase() ?: Theme.SYSTEM_DEFAULT
        }
    }

    fun saveTheme(theme: Theme) {
        launch(true) {
            setThemeUseCase(theme)
            _savedUI.value = theme
        }
    }

}