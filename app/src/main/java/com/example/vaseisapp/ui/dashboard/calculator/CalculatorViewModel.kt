package com.example.vaseisapp.ui.dashboard.calculator

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.CalculatorExamType
import com.example.vaseisapp.usecase.calculator.GetAllExamsTypesUseCase
import com.example.vaseisapp.usecase.prefs.GetExamTypeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CalculatorViewModel @ViewModelInject constructor(
    private val getAllExamsTypesUseCase: GetAllExamsTypesUseCase,
    private val getExamTypeUseCase: GetExamTypeUseCase
) : BaseViewModel() {

    private var _examsTypes = MutableLiveData<List<CalculatorExamType>>()
    val examsTypes: LiveData<List<CalculatorExamType>> = _examsTypes

    private var _resultUI = MutableLiveData<String>()
    val resultUI: LiveData<String> = _resultUI

    private var _examTypePref = MutableLiveData<Int>()
    val examTypePref: LiveData<Int> = _examTypePref

    fun loadData() {
        launch(true) {

            val examsTypes = getAllExamsTypesUseCase() ?: listOf()
            val pref = getExamTypeUseCase()
            _examsTypes.value = examsTypes
            _examTypePref.value = examsTypes.indexOfFirst { it.id == pref.id } ?: 0

            /*_examTypePref.value =
                if (examsTypeCalculators.indexOfFirst { examType -> examType.id == pref } != -1) examsTypeCalculators.indexOfFirst { examType -> examType.id == pref } else 0*/
        }
    }

    fun changeResult(result: String) {
        _resultUI.value = result
    }
}