package com.vaseis.app.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.prefs.Language
import com.vaseis.app.usecase.calculator.GetAllExamsTypesUseCase
import com.vaseis.app.usecase.calculator.SaveAllExamsTypesLocal
import com.vaseis.app.usecase.prefs.GetLanguageUseCase
import kotlinx.coroutines.delay

class SplashViewModel @ViewModelInject constructor(
    private val saveAllExamsTypesLocal: SaveAllExamsTypesLocal,
    private val getAllExamsTypesUseCase: GetAllExamsTypesUseCase,
    private val getLanguageUseCase: GetLanguageUseCase
) : BaseViewModel() {
    private var _showMainUi = MutableLiveData<Unit>()
    val showMainUi: LiveData<Unit> = _showMainUi

    private var _langPref = MutableLiveData<Language>()
    val langPref: LiveData<Language> = _langPref

    fun loadData() {
        launch(true) {
            delay(1000)
            _langPref.value = getLanguageUseCase() ?: Language.SYSTEM_DEFAULT
            _showMainUi.value = Unit
        }
    }
}