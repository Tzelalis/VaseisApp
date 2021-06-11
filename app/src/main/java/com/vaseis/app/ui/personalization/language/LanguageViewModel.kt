package com.vaseis.app.ui.personalization.language

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.usecase.prefs.GetLanguageUseCase
import com.vaseis.app.usecase.prefs.SetLanguageUseCase


class LanguageViewModel @ViewModelInject constructor(
    private val setLanguageUseCase: SetLanguageUseCase,
    private val getLanguageUseCase: GetLanguageUseCase
) : BaseViewModel() {
    private var _currentLangUI = MutableLiveData<Language>()
    val currentLangUI: LiveData<Language> = _currentLangUI

    private var _savedLangUI = MutableLiveData<Language>()
    val savedLangUI: LiveData<Language> = _savedLangUI


    fun loadLanguage() {
        launch(true) {
            _currentLangUI.value = getLanguageUseCase()!!
        }
    }

    fun saveLanguage(lang: Language) {
        launch(true)    {
            setLanguageUseCase(lang)
            _savedLangUI.value = lang
        }
    }
}