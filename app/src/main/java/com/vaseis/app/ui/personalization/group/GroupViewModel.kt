package com.vaseis.app.ui.personalization.group

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty
import com.vaseis.app.ui.dashboard.calculator.model.GroupItem
import com.vaseis.app.ui.dashboard.calculator.model.map
import com.vaseis.app.usecase.calculator.GetAllExamsTypesUseCase
import com.vaseis.app.usecase.prefs.GetExamTypeUseCase
import com.vaseis.app.usecase.prefs.GetGroupTypeUseCase
import com.vaseis.app.usecase.prefs.SetGroupTypeUseCase

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

            val listOfItems = map(examType?.calculatorGroups)
            listOfItems.firstOrNull { item ->
                item.calculatorGroup.id == getGroupTypeUseCase().id
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