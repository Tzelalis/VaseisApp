package com.vaseis.app.ui.dashboard.calculator.group

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.calculation.entities.CalculatorGroup
import com.vaseis.app.domain.calculation.entities.CalculatorLesson
import com.vaseis.app.ui.dashboard.calculator.model.GroupItem
import com.vaseis.app.ui.dashboard.calculator.model.LessonItem
import com.vaseis.app.ui.dashboard.calculator.model.map
import com.vaseis.app.usecase.prefs.GetPrefsFieldsUseCase

class CalculatorGroupViewModel @ViewModelInject constructor() : BaseViewModel() {

    private var _groupsUI = MutableLiveData<List<GroupItem>>()
    val groupsUI: LiveData<List<GroupItem>> = _groupsUI

    fun loadGroups(list: List<CalculatorGroup>) {
        launch(true) {
            val groups = map(list)

            try {
                groups[0].isSelected = true
            }catch (e : ArrayIndexOutOfBoundsException){}

            _groupsUI.value = groups
        }
    }
}