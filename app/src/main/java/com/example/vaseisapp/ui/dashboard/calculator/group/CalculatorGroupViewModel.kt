package com.example.vaseisapp.ui.dashboard.calculator.group

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.Group
import com.example.vaseisapp.ui.dashboard.calculator.model.GroupItem
import com.example.vaseisapp.ui.dashboard.calculator.model.map

class CalculatorGroupViewModel @ViewModelInject constructor() : BaseViewModel(){

    private var _groupsUI = MutableLiveData<List<GroupItem>>()
    val groupsUI : LiveData<List<GroupItem>> = _groupsUI

    fun loadGroups(list: List<Group>) {
        _groupsUI.value = map(list)
    }
}