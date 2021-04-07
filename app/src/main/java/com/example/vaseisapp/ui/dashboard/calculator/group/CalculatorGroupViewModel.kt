package com.example.vaseisapp.ui.dashboard.calculator.group

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.Group
import com.example.vaseisapp.domain.calculation.entities.Lesson
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import com.example.vaseisapp.ui.dashboard.calculator.model.LessonItem
import com.example.vaseisapp.ui.dashboard.calculator.model.map
import com.example.vaseisapp.usecase.prefs.GetGroupTypeUseCase

class CalculatorGroupViewModel @ViewModelInject constructor(private val prefGroup : GetGroupTypeUseCase) : BaseViewModel(){

    private var _groupsUI = MutableLiveData<List<GroupItem>>()
    val groupsUI : LiveData<List<GroupItem>> = _groupsUI

    private var _lessonsUI = MutableLiveData<List<LessonItem>>()
    val lessonsUI : LiveData<List<LessonItem>> = _lessonsUI

    private var _visibleUI = MutableLiveData<Unit>()
    val visibleUI : LiveData<Unit> = _visibleUI

    fun loadGroups(list: List<Group>) {
        launch(true)    {
            val groups = map(list)
            val prefGroup = prefGroup()

            (groups.firstOrNull { it.group.id == prefGroup.id } ?: groups[0]).isSelected = true

            _groupsUI.value = groups
        }
    }

    fun loadLessons(list : List<Lesson>)    {
        launch(true){
            _lessonsUI.value = map(list)
        }
    }

    fun setVisibleViews()   {
        launch(true, 1000){
            _visibleUI.value = Unit
        }
    }


}