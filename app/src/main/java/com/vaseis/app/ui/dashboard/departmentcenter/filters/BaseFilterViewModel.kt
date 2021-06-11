package com.vaseis.app.ui.dashboard.departmentcenter.filters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.data.bases.mapper.UniversityFilterItemMapper
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.CityFilterItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.vaseis.app.usecase.bases.FetchAllUniversitiesUseCase

class BaseFilterViewModel @ViewModelInject constructor(
    private val getAllUniversitiesUseCase: FetchAllUniversitiesUseCase,
    private val universityFilterItemMapper: UniversityFilterItemMapper
) : BaseViewModel() {

    private val _universitiesUI = MutableLiveData<List<UniversityFilterItem>>()
    val universityUI: LiveData<List<UniversityFilterItem>> = _universitiesUI

    private val _citiesUI = MutableLiveData<List<CityFilterItem>>()
    val citiesUI: LiveData<List<CityFilterItem>> = _citiesUI

    fun loadUniversities() {
        launch(true) {
            val response = getAllUniversitiesUseCase()
            val items = universityFilterItemMapper(response.records)

            _universitiesUI.value = items
        }
    }

    fun loadCities()    {
        _citiesUI.value = listOf()
    }
}