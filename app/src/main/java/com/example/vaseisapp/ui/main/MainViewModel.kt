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
    private var _navigateUI = SingleLiveEvent<NavDirections>()
    val navigationUI: LiveData<NavDirections> = _navigateUI

    fun navigateTo(currentItemId: Int, destinationItemId: Int) {
        launch(true) {
            if (currentItemId == destinationItemId)
                return@launch

            when (currentItemId) {
                R.id.basesItem -> {
                    when (destinationItemId) {
                        R.id.topicsItem -> _navigateUI.value = DepartmentFragmentDirections.actionDepartmentToTopics()
                        R.id.calculatorItem -> _navigateUI.value = DepartmentFragmentDirections.actionDepartmentToCalculator()
                        R.id.accountItem -> _navigateUI.value = DepartmentFragmentDirections.actionDepartmentToAccount()
                    }
                }
                R.id.topicsItem -> {
                    when (destinationItemId) {
                        R.id.basesItem -> _navigateUI.value = TopicsFragmentDirections.actionTopicsToDepartment()
                        R.id.calculatorItem -> _navigateUI.value = TopicsFragmentDirections.actionTopicsToCalculator()
                        R.id.accountItem -> _navigateUI.value = TopicsFragmentDirections.actionTopicsToAccount()
                    }
                }
                R.id.calculatorItem -> {
                    when (destinationItemId) {
                        R.id.basesItem -> _navigateUI.value = CalculatorFragmentDirections.actionCalculatorToDepartment()
                        R.id.topicsItem -> _navigateUI.value = CalculatorFragmentDirections.actionCalculatorToTopics()
                        R.id.accountItem -> _navigateUI.value = CalculatorFragmentDirections.actionCalculatorToAccount()
                    }
                }
                R.id.accountItem -> {
                    when (destinationItemId) {
                        R.id.basesItem -> _navigateUI.value = AccountFragmentDirections.actionAccountToDepartment()
                        R.id.topicsItem -> _navigateUI.value = AccountFragmentDirections.actionAccountToTopics()
                        R.id.calculatorItem -> _navigateUI.value = AccountFragmentDirections.actionAccountToCalculator()
                    }
                }
            }
        }
    }
}