package com.example.vaseisapp.ui.personalization.group

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import com.example.vaseisapp.ui.dashboard.calculator.model.map
import com.example.vaseisapp.usecase.calculator.GetAllExamsTypesUseCase
import com.example.vaseisapp.usecase.prefs.GetExamTypeUseCase
import com.example.vaseisapp.usecase.prefs.GetGroupTypeUseCase
import com.example.vaseisapp.usecase.prefs.SetGroupTypeUseCase

class GroupViewModel @ViewModelInject constructor(
    private val getAllExamsTypesUseCase: GetAllExamsTypesUseCase,
    private val getExamTypeUseCase: GetExamTypeUseCase,
    private val getGroupTypeUseCase: GetGroupTypeUseCase,
    private val setGroupTypeUseCase: SetGroupTypeUseCase
) : BaseViewModel(){

    private var _groupsUI = MutableLiveData<List<GroupItem>>()
    val groupsUI : LiveData<List<GroupItem>> = _groupsUI

    private var _savedUI = MutableLiveData<Unit>()
    val savedUI : LiveData<Unit> = _savedUI

    fun loadData()  {
        launch(true)    {
            val listOfExamTypes = getAllExamsTypesUseCase()
            val examTypePref = getExamTypeUseCase()
            val examType = listOfExamTypes.firstOrNull { examType -> examType.id == examTypePref.id   }

            val listOfItems = map(examType?.groups)
            listOfItems.firstOrNull { item ->
                item.group.id == getGroupTypeUseCase().id
            }?.isSelected = true

            _groupsUI.value = listOfItems
        }
    }

    fun savedData(property : PrefProperty) {
        launch(true)    {
            setGroupTypeUseCase(property)
            _savedUI.value = Unit
        }

    }
}