package com.vaseis.app.ui.dashboard.departmentcenter.department

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.DepartmentWithSelected
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.map
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.Filter
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.OrderType
import com.vaseis.app.usecase.bases.FetchAllDepartmentsUseCase
import com.vaseis.app.usecase.bases.FetchAllUniversitiesUseCase
import com.vaseis.app.utils.SingleLiveEvent

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


    fun loadDepartments(filter: Filter?) {
        launch(true) {
            val list = map(departmentsUseCase().toMutableList())
            _departments.value = list

            if (filter == null) {
                _departmentsFiltered.value = list.filter { it.department.isActive }.sortedBy { it.department.name }
            } else {
                filterList(filter)
            }
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

    fun filterList(filter: Filter) {
        _departments.value?.let { filteredList ->
            var list = filteredList
            if (!filter.showDisabledDepartments)
                list = list.filter { it.department.isActive }

            val list2 = mutableListOf<DepartmentWithSelected>()
            if (filter.universities.isEmpty())
                list2.addAll(list)
            else
                for (uniId in filter.universities) {
                    list2.addAll(list.filter { it.department.uniId == uniId })
                }

            val list3 = mutableListOf<DepartmentWithSelected>()
            if (filter.fields.isEmpty())
                list3.addAll(list2)
            else {
                for (dept in list2)
                    for (deptFields in dept.department.fields) {
                        var flag = false
                        for (filterField in filter.fields) {
                            if (deptFields == filterField.key) {
                                list3.add(dept)
                                flag = true
                                break
                            }
                        }
                        if (flag)
                            break
                    }
            }

            val list4 = when (filter.order) {
                OrderType.ALPHABETICAL -> list3.sortedBy { it.department.name }
                OrderType.UNIVERSITY -> list3.sortedBy { it.department.uniFullTitle }
            }

            _departmentsFiltered.value = list4
        }
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