package com.example.vaseisapp.ui.dashboard.calculator

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.calculation.entities.ExamType
import com.example.vaseisapp.ui.dashboard.accountcenter.model.PrefProperty
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

    private var _examTypePref = MutableLiveData<String>()
    val examTypePref : LiveData<String> = _examTypePref

    fun loadData() {
        launch(true) {
            _examsTypes.value = getAllExamsTypesUseCase() ?: listOf()
            _examTypePref.value = getExamTypeUseCase().name ?: ""
        }
    }

    fun changeResult(result: String) {
        _resultUI.value = result
    }
}