package com.example.vaseisapp.ui.personalization.language

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.prefs.Language
import com.example.vaseisapp.usecase.prefs.GetLanguageUseCase
import com.example.vaseisapp.usecase.prefs.SetLanguageUseCase


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