package com.example.vaseisapp.ui.dashboard.accountcenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertiesFromPrefs
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PropertyItem
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.usecase.prefs.GetExamTypeUseCase
import com.example.vaseisapp.usecase.prefs.GetGroupTypeUseCase
import com.example.vaseisapp.usecase.prefs.GetLanguageUseCase
import com.example.vaseisapp.utils.SingleLiveEvent

class AccountViewModel @ViewModelInject constructor(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getExamTypeUseCase: GetExamTypeUseCase,
    private val getGroupTypeUseCase: GetGroupTypeUseCase
) : BaseViewModel() {
    private var _properties = MutableLiveData<List<List<PropertyItem>>>()
    val properties : LiveData<List<List<PropertyItem>>> = _properties

    private var _propertiesUI = SingleLiveEvent<PropertiesFromPrefs>()
    val propertiesUI : LiveData<PropertiesFromPrefs> = _propertiesUI

    fun loadData()    {
        launch(true) {
            val langString = when (getLanguageUseCase().code) {
                "el" -> R.string.account_language_greek
                "en" -> R.string.account_language_english
                else -> R.string.language_system_default
            }
            val exams = getExamTypeUseCase()
            val group = getGroupTypeUseCase()

            _propertiesUI.value = PropertiesFromPrefs(langString, exams.name, group.name)
        }
    }
}