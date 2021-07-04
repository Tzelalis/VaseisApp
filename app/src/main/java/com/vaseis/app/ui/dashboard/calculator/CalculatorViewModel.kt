package com.vaseis.app.ui.dashboard.calculator

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.calculation.entities.CalculatorExamType
import com.vaseis.app.usecase.calculator.GetAllExamsTypesUseCase
import com.vaseis.app.usecase.prefs.GetExamTypeUseCase

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

            val examsTypes = getAllExamsTypesUseCase()
            val pref = getExamTypeUseCase()
            _examsTypes.value = examsTypes
            _examTypePref.value = examsTypes.indexOfFirst { it.examTypeId == pref.id } ?: 0

            _examTypePref.value =
                if (examsTypes.indexOfFirst { examType -> examType.examTypeId == pref.id } != -1) examsTypes.indexOfFirst { examType -> examType.examTypeId == pref.id } else 0
        }
    }

    fun changeResult(result: String) {
        _resultUI.value = result
    }
}