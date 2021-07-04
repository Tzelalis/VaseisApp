package com.vaseis.app.ui.dashboard.accountcenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.R
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.domain.prefs.Theme
import com.vaseis.app.ui.dashboard.accountcenter.model.PropertiesFromPrefs
import com.vaseis.app.ui.dashboard.accountcenter.model.PropertyItem
import com.vaseis.app.usecase.prefs.GetExamTypeUseCase
import com.vaseis.app.usecase.prefs.GetPrefsFieldsUseCase
import com.vaseis.app.usecase.prefs.GetLanguageUseCase
import com.vaseis.app.usecase.prefs.GetThemeUseCase
import com.vaseis.app.utils.SingleLiveEvent

class AccountViewModel @ViewModelInject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getExamTypeUseCase: GetExamTypeUseCase,
    private val getPrefsFieldsUseCase: GetPrefsFieldsUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : BaseViewModel() {
    private var _properties = MutableLiveData<List<List<PropertyItem>>>()
    val properties : LiveData<List<List<PropertyItem>>> = _properties

    private var _propertiesUI = SingleLiveEvent<PropertiesFromPrefs>()
    val propertiesUI : LiveData<PropertiesFromPrefs> = _propertiesUI

    fun loadData()    {
        launch(true) {
            val langString = when (getLanguageUseCase()) {
                Language.GREEK -> R.string.account_language_greek
                Language.ENGLISH -> R.string.account_language_english
                else -> R.string.language_system_default
            }

            val themeString = when(getThemeUseCase()){
                Theme.SYSTEM_DEFAULT -> R.string.theme_default
                Theme.LIGHT -> R.string.theme_light
                Theme.DARK -> R.string.theme_dark
            }

            val exams = getExamTypeUseCase()
            val group = getPrefsFieldsUseCase()

            _propertiesUI.value = PropertiesFromPrefs(langString, themeString, exams.name, group)
        }
    }
}