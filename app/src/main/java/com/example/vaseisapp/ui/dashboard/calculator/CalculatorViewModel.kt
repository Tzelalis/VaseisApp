package com.example.vaseisapp.ui.dashboard.calculator

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.example.vaseisapp.usecase.calculator.GetAllExamsTypesUseCase
import com.example.vaseisapp.usecase.prefs.GetExamTypeUseCase

class CalculatorViewModel @ViewModelInject constructor(
    private val getAllExamsTypesUseCase: GetAllExamsTypesUseCase,
    private val getExamTypeUseCase: GetExamTypeUseCase
) : BaseViewModel() {

    private var _examsTypes = MutableLiveData<List<ExamType>>()
    val examsTypes: LiveData<List<ExamType>> = _examsTypes

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
                if (examsTypes.indexOfFirst { examType -> examType.id == pref } != -1) examsTypes.indexOfFirst { examType -> examType.id == pref } else 0*/
        }
    }

    fun changeResult(result: String) {
        _resultUI.value = result
    }
}