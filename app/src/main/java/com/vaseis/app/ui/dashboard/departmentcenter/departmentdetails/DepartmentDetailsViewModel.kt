package com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails

import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.bases.entities.DepartmentBases
import com.vaseis.app.domain.bases.entities.StatsDept
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentDetailsArguments
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentItem
import com.vaseis.app.ui.dashboard.departmentcenter.departmentdetails.model.LIST_OF_COLORS
import com.vaseis.app.usecase.bases.GetDepartmentBases
import com.vaseis.app.usecase.bases.GetDeptStatsUseCase
import com.github.mikephil.charting.data.Entry
import com.vaseis.app.domain.bases.entities.DeptInfo
import com.vaseis.app.usecase.bases.GetDepartmentInfo
import kotlinx.coroutines.launch
import java.io.IOException

class DepartmentDetailsViewModel @ViewModelInject constructor(
    private val getDepartmentBases: GetDepartmentBases,
    private val getDeptStatsUseCase: GetDeptStatsUseCase,
    private val getDepartmentInfo: GetDepartmentInfo
) : BaseViewModel() {
    private var _departmentItems = MutableLiveData<List<DepartmentItem>>()
    val departmentItem = _departmentItems

    private var _singleDepartment = MutableLiveData<DepartmentBases>()
    val singleDepartment = _singleDepartment

    private var _singleStats = MutableLiveData<StatsDept>()
    val singleStats: LiveData<StatsDept> = _singleStats

    private var _singleDeptInfo = MutableLiveData<DeptInfo>()
    val singleDeptInfo: LiveData<DeptInfo> = _singleDeptInfo

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadSelectedDepartments(departments: Array<DepartmentDetailsArguments>) {
        launch {
            val departmentItems = mutableListOf<DepartmentItem>()

            for (i in departments.indices) {
                val result = getDepartmentBases(departments[i].code, "gel-ime-gen")

                for (resultDept in result) {
                    val entriesList = mutableListOf<Entry>()
                    for (base in resultDept.bases) {
                        if (base.examType == "ΓΕΛ ΕΣΠΕΡΙΝΑ" || base.examType == "ΓΕΛ ΝΕΟ ΗΜΕΡΗΣΙΑ" || base.examType == "ΓΕΛ, ΕΠΑΛΒ ΗΜΕΡΗΣΙΑ")
                            entriesList.add(Entry(base.year.toFloat(), base.baseLast.toFloat()))
                    }

                    departmentItems.add(
                        DepartmentItem(
                            resultDept.code,
                            resultDept.deptName,
                            resultDept.uniTitle,
                            resultDept.uniTitleShort,
                            LIST_OF_COLORS[i],
                            false,
                            entriesList
                        )
                    )
                }
            }

            _departmentItems.value = departmentItems
        }
    }

    fun loadSingleDepartment(code: String, type : String? = null) {
        viewModelScope.launch {
            if(type==null)  {
                val result = getDepartmentInfo(code)
                _singleDeptInfo.value = result
                return@launch
            }

            try {
                val result = getDepartmentBases(code, type)
                _singleDepartment.value = result[0]
            } catch (e : IOException)   {
                _error.value = e.message
            }
        }
    }

    fun loadSingleDeptStats(code: String) {
        launch {
            val response = getDeptStatsUseCase(code)
            _singleStats.value = response
        }
    }
}

private fun departmentPopularity() {
    //todo return a double about popularity of department. Calculate with positions (firstPos*3 + secondPos*2...etc)
}




