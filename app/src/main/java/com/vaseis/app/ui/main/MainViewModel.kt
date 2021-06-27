package com.vaseis.app.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDirections
import com.vaseis.app.R
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.domain.bases.entities.Field
import com.vaseis.app.domain.properties.PropertiesExamType
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.FilterChip
import com.vaseis.app.ui.dashboard.departmentcenter.department.models.FilterChipType
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.Filter
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.OrderType
import com.vaseis.app.utils.SingleLiveEvent
import java.util.*

class MainViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private var _navigateUI = SingleLiveEvent<Int>()
    val navigationUI: LiveData<Int> = _navigateUI

    private var _navigateFromAppNavGraphUI = SingleLiveEvent<NavDirections>()
    val navigateFromAppNavGraphUI: LiveData<NavDirections> = _navigateFromAppNavGraphUI

    private var _filterSavedState = MutableLiveData<Filter>()
    val filterSavedState: LiveData<Filter> = _filterSavedState

    private var _tempFilterSavedState = MutableLiveData<Filter>()
    val tempFilterSavedState: LiveData<Filter> = _tempFilterSavedState

    fun navigateTo(currentItemId: Int, destinationItemId: Int) {
        launch(true) {
            if (currentItemId == destinationItemId)
                return@launch

            when (destinationItemId) {
                R.id.basesItem -> {
                    _navigateUI.value = R.id.action_to_Bases
                }
                R.id.topicsItem -> {
                    _navigateUI.value = R.id.action_to_Topics
                }
                R.id.calculatorItem -> {
                    _navigateUI.value = R.id.action_to_Calculator
                }
                R.id.accountItem -> {
                    _navigateUI.value = R.id.action_to_Account
                }
            }
        }
    }

    fun navigate(action: NavDirections) {
        _navigateFromAppNavGraphUI.value = action
    }

    fun setFilter() {
        _filterSavedState.value = tempFilterSavedState.value
    }

    fun initTempFilter() {
        if (filterSavedState.value == null)
            _filterSavedState.value = Filter()

        if (_tempFilterSavedState.value == null)
            _tempFilterSavedState.value = Filter()

        //_tempFilterSavedState.value = filterSavedState.value ?: Filter()
    }

    fun clearFilter() {
        _filterSavedState.value = Filter()
    }

    fun clearTempFilter() {
        _tempFilterSavedState.value = Filter()
    }

    fun setExamType(examType: PropertiesExamType) {
        _tempFilterSavedState.value?.examType = examType
    }

    fun setDisabledDepartments(flag: Boolean) {
        _tempFilterSavedState.value?.showDisabledDepartments = flag
    }

    fun setOrder(orderType: OrderType) {
        _tempFilterSavedState.value?.order = orderType
    }

    fun setUniversities(universities: List<String>) {
        _tempFilterSavedState.value?.universities = universities
    }

    fun setFields(fields: MutableList<Field>) {
        _tempFilterSavedState.value?.fields = fields
    }

    fun removeFilter(chip: FilterChip) {
        val temp = _filterSavedState.value ?: Filter()
        when (chip.type) {
            FilterChipType.EXAM_TYPE -> {
                temp.examType = PropertiesExamType("1", "", "ΓΕΛ", "gel-ime-gen")
                temp.let { _filterSavedState.value = it }
            }
            FilterChipType.DISABLE_DEPARTMENTS -> {
                temp.showDisabledDepartments = false
                temp.let { _filterSavedState.value = it }
            }
            FilterChipType.ORDER -> {
                temp.order = OrderType.ALPHABETICAL
                temp.let { _filterSavedState.value = it }
            }
            FilterChipType.FIELD -> {
                temp.fields.remove(temp.fields.first { it.key == chip.id })
                temp.let { _filterSavedState.value = it }
            }
            FilterChipType.UNIVERSITIES -> {
                temp.universities = listOf()
                temp.let { _filterSavedState.value = it }
            }
        }
    }
}