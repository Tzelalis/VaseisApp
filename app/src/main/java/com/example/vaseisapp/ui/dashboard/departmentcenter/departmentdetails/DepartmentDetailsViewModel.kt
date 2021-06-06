package com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.bases.entities.DepartmentBases
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentDetailsArguments
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.DepartmentItem
import com.example.vaseisapp.ui.dashboard.departmentcenter.departmentdetails.model.LIST_OF_COLORS
import com.example.vaseisapp.usecase.bases.GetDepartmentBases
import com.github.mikephil.charting.data.Entry

class DepartmentDetailsViewModel @ViewModelInject constructor(
    private val getDepartmentBases: GetDepartmentBases
) : BaseViewModel() {
    private var _departmentItems = MutableLiveData<List<DepartmentItem>>()
    val departmentItem = _departmentItems

    private var _singleDepartment = MutableLiveData<DepartmentBases>()
    val singleDepartment = _singleDepartment

    fun loadSelectedDepartments(departments: Array<DepartmentDetailsArguments>) {
        launch(true) {
            val departmentItems = mutableListOf<DepartmentItem>()

            for (i in departments.indices) {
                val result = getDepartmentBases(departments[i].code)

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

    fun loadSingleDepartment(code: String) {
        launch(true) {
            val result = getDepartmentBases(code)

            _singleDepartment.value = result[0]
        }
    }
}

private fun departmentPopularity() {
    //todo return a double about popularity of department. Calculate with positions (firstPos*3 + secondPos*2...etc)
}




