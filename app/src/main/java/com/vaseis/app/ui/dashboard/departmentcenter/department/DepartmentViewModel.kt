package com.vaseis.app.ui.dashboard.departmentcenter.department

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.map
import com.vaseis.app.usecase.bases.FetchAllDepartmentsUseCase
import com.vaseis.app.usecase.bases.FetchAllUniversitiesUseCase
import com.vaseis.app.utils.SingleLiveEvent
import java.util.*

class DepartmentViewModel @ViewModelInject constructor(
    private val universityUseCase: FetchAllUniversitiesUseCase,
    private val departmentsUseCase: FetchAllDepartmentsUseCase
) : BaseViewModel() {
    private var _departments = MutableLiveData<List<DepartmentWithSelected>>()
    val departments = _departments

    private var _departmentsFiltered = MutableLiveData<List<DepartmentWithSelected>>()
    val departmentsFiltered = _departmentsFiltered

    private var _showDepartmentDetailsUI = SingleLiveEvent<Boolean>()
    val showDepartmentDetailsUI = _showDepartmentDetailsUI


    fun loadDepartments() {
        launch(true) {
            val list = map(departmentsUseCase().toMutableList())
            _departments.value = list

            _departmentsFiltered.value = list.sortedBy { it.name }
        }
    }

    fun showDepartmentDetailsUI(delay: Long) {
        launch(true, delayValue = delay) {
            _showDepartmentDetailsUI.value = true
        }
    }

    fun setDepartmentsIsSelected(position: Int) {
        _departments.value?.let { list ->
            list[position].isSelected = !list[position].isSelected
        }
    }

    fun filterList(input: String) {
        if (departments.value.isNullOrEmpty()) {
            return
        }

        val list = departments.value?.filter {
            it.name.toUpperCase(Locale.getDefault()).contains(input.toUpperCase(Locale.getDefault()))
                    || it.uniTitle.toUpperCase(Locale.getDefault()).contains(input.toUpperCase(Locale.getDefault()))
                    || it.uniFullTitle.toUpperCase(Locale.getDefault()).contains(input.toUpperCase(Locale.getDefault()))
        }

        _departmentsFiltered.value = list?.sortedBy { it.name } ?: listOf()
    }

    /*private fun List<DepartmentWithSelected>.sortByCode() : List<DepartmentWithSelected> {
        //todo make functionality
        return try {
            this.sortedBy { Integer.parseInt(it.code) }
        } catch (e: NumberFormatException) {
            Log.e("APIERROR", "There is a no number code!")
            this.sortedBy { it.code }
        }
    }*/
}