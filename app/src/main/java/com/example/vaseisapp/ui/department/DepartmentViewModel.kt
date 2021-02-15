package com.example.vaseisapp.ui.department

import androidx.core.view.isVisible
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.ui.departmentdetails.DepartmentItem
import com.example.vaseisapp.usecase.department.FetchAllDepartmentsUseCase
import com.example.vaseisapp.usecase.university.FetchAllUniversitiesUseCase
import com.example.vaseisapp.utils.SingleLiveEvent
import kotlinx.coroutines.delay
import java.util.*

class DepartmentViewModel @ViewModelInject constructor(
    private val universityUseCase: FetchAllUniversitiesUseCase,
    private val departmentsUseCase: FetchAllDepartmentsUseCase
    )  : BaseViewModel(){
    private var _departments = MutableLiveData<List<DepartmentWithSelected>>()
    val departments = _departments

    private var _departmentsFiltered = MutableLiveData<List<DepartmentWithSelected>>()
    val departmentsFiltered = _departments

    private var _showDepartementDetailsUI = SingleLiveEvent<Boolean>()
    val showDepartmentDetailsUI = _showDepartementDetailsUI


    fun loadDepartments()  {
        launch(true)    {
            val list = map(departmentsUseCase().toMutableList())
            _departments.value = list
            _departmentsFiltered.value =  list
        }
    }

    fun showDepartmentDetailsUI(delay : Long){
        launch(true, delayValue = delay){
            _showDepartementDetailsUI.value = true
        }
    }

    fun setDepartmentsIsSelected(position : Int){
        _departments.value?.let { list ->
            list[position].isSelected = !list[position].isSelected
        }
    }

    fun filterList(input : String){
        if(departments.value.isNullOrEmpty())
            return

        if(input.isNotEmpty()){
            val list = mutableListOf<DepartmentWithSelected>()

            for (item in departments.value!!) {
                if (item.name.toUpperCase(Locale.getDefault()).contains(input.toUpperCase(Locale.getDefault()))
                    || item.code.toString().contains(input)
                ) {
                    list.add(item)
                }
            }

            _departmentsFiltered.value = list
            return
        }

        _departmentsFiltered.value = _departments.value
    }
}