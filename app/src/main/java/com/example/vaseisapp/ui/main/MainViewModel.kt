package com.example.vaseisapp.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.example.vaseisapp.R
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.ui.dashboard.accountcenter.AccountFragmentDirections
import com.example.vaseisapp.ui.dashboard.calculator.CalculatorFragmentDirections
import com.example.vaseisapp.ui.dashboard.departmentcenter.department.DepartmentFragmentDirections
import com.example.vaseisapp.ui.dashboard.topicscenter.TopicsFragmentDirections
import com.example.vaseisapp.utils.SingleLiveEvent

class MainViewModel @ViewModelInject constructor() : BaseViewModel() {
    private var _navigateUI = SingleLiveEvent<Int>()
    val navigationUI: LiveData<Int> = _navigateUI

    private var _navigateFromAppNavGraphUI = SingleLiveEvent<NavDirections>()
    val navigateFromAppNavGraphUI: LiveData<NavDirections> = _navigateFromAppNavGraphUI

    fun navigateTo(currentItemId: Int, destinationItemId: Int) {
        launch(true) {
            if (currentItemId == destinationItemId)
                return@launch

            when (destinationItemId) {
                R.id.basesItem -> {
                    _navigateUI.value = R.id.action_to_Bases
                }
                R.id.topicsItem -> {
                    _navigateUI.value = R.id.action_to_Topics
                }
                R.id.calculatorItem -> {
                    _navigateUI.value = R.id.action_to_Calculator
                }
                R.id.accountItem -> {
                    _navigateUI.value = R.id.action_to_Account
                }
            }
        }
    }

    fun navigate(action : NavDirections)  {
        _navigateFromAppNavGraphUI.value = action
    }
}