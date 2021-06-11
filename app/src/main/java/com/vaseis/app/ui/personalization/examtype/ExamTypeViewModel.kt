package com.vaseis.app.ui.personalization.examtype

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty
import com.vaseis.app.ui.dashboard.topicscenter.model.ExamTypeItem
import com.vaseis.app.ui.dashboard.topicscenter.model.map
import com.vaseis.app.usecase.calculator.GetPropertiesExamsTypes
import com.vaseis.app.usecase.prefs.GetExamTypeUseCase
import com.vaseis.app.usecase.prefs.SetExamTypeUseCase
import com.vaseis.app.usecase.prefs.SetGroupTypeUseCase

class ExamTypeViewModel @ViewModelInject constructor(
    private val getAllExamsTypesUseCase: GetPropertiesExamsTypes,
    private val setExamTypeUseCase: SetExamTypeUseCase,
    private val getExamTypeUseCase: GetExamTypeUseCase,
    private val setGroupTypeUseCase: SetGroupTypeUseCase
) : BaseViewModel() {
    private var _typesUI = MutableLiveData<List<ExamTypeItem>>()
    val typesUI : LiveData<List<ExamTypeItem>> = _typesUI

    private var _savedUI = MutableLiveData<Unit>()
    val savedUI : LiveData<Unit> = _savedUI

    fun loadTypes() {
        launch(true)    {
            val list = map(getAllExamsTypesUseCase())
            val savedExamType = getExamTypeUseCase()

            list.firstOrNull { it.examType.id == savedExamType.id}?.isSelected = true

            _typesUI.value = list
        }
    }

    fun saveTypes(property : PrefProperty) {
        launch(true){
            if(property.id != getExamTypeUseCase().id)
                setGroupTypeUseCase(PrefProperty("", ""))

            setExamTypeUseCase(property)
            _savedUI.value = Unit
        }
    }
}