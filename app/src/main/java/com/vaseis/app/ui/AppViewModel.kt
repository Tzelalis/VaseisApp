package com.vaseis.app.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.prefs.Theme
import com.vaseis.app.usecase.prefs.GetLanguageUseCase
import com.vaseis.app.usecase.prefs.GetThemeUseCase

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