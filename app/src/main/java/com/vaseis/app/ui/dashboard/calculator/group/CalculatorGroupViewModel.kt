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
import com.vaseis.app.usecase.prefs.GetGroupTypeUseCase

class CalculatorGroupViewModel @ViewModelInject constructor(private val prefGroup : GetGroupTypeUseCase) : BaseViewModel(){

    private var _groupsUI = MutableLiveData<List<GroupItem>>()
    val groupsUI : LiveData<List<GroupItem>> = _groupsUI

    private var _lessonsUI = MutableLiveData<List<LessonItem>>()
    val lessonsUI : LiveData<List<LessonItem>> = _lessonsUI

    fun loadGroups(list: List<CalculatorGroup>) {
        launch(true)    {
            val groups = map(list)
            val prefGroup = prefGroup()

            (groups.firstOrNull { it.calculatorGroup.id == prefGroup.id } ?: groups[0]).isSelected = true

            _groupsUI.value = groups
        }
    }

    fun loadLessons(list : List<CalculatorLesson>)    {
        launch(true){
            _lessonsUI.value = map(list)
        }
    }


}