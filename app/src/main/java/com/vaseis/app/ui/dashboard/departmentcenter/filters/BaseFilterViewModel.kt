package com.vaseis.app.ui.dashboard.departmentcenter.filters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.data.bases.mapper.UniversityFilterItemMapper
import com.vaseis.app.domain.properties.ExamType
import com.vaseis.app.ui.dashboard.departmentcenter.filters.mappers.BaseFilterExamTypeItemMapper
import com.vaseis.app.ui.dashboard.departmentcenter.filters.mappers.BaseFilterFieldMapper
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.BaseFilterExamTypeItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.CityFilterItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.FieldFilterItem
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.UniversityFilterItem
import com.vaseis.app.usecase.bases.FetchAllUniversitiesUseCase
import com.vaseis.app.usecase.calculator.GetAllExamsTypesUseCase
import com.vaseis.app.usecase.calculator.GetPropertiesExamsTypes
import com.vaseis.app.usecase.prefs.GetExamTypeUseCase
import com.vaseis.app.usecase.properties.GetFieldsUseCase

class BaseFilterViewModel @ViewModelInject constructor(
    private val getAllUniversitiesUseCase: FetchAllUniversitiesUseCase,
    private val universityFilterItemMapper: UniversityFilterItemMapper,
    private val getAllExamsTypesUseCase: GetPropertiesExamsTypes,
    private val getExamTypeUseCase: GetExamTypeUseCase,
    private val baseFilterExamTypeItemMapper : BaseFilterExamTypeItemMapper,
    private val fieldsUseCase: GetFieldsUseCase,
    private val fieldsMapper: BaseFilterFieldMapper
) : BaseViewModel() {

    private val _universitiesUI = MutableLiveData<List<UniversityFilterItem>>()
    val universityUI: LiveData<List<UniversityFilterItem>> = _universitiesUI

    private val _citiesUI = MutableLiveData<List<CityFilterItem>>()
    val citiesUI: LiveData<List<CityFilterItem>> = _citiesUI

    private val _examTypesItemUI = MutableLiveData<List<BaseFilterExamTypeItem>>()
    val examTypeItemUI : LiveData<List<BaseFilterExamTypeItem>> = _examTypesItemUI

    private val _fieldsItemUI = MutableLiveData<List<FieldFilterItem>>()
    val fieldsItemUI : LiveData<List<FieldFilterItem>> = _fieldsItemUI

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

    fun loadExamTypes() {
        launch {
            val response = baseFilterExamTypeItemMapper(getAllExamsTypesUseCase(ExamType.FILTERS))
            _examTypesItemUI.value = response
        }
    }

    fun loadFields()    {
        launch {
            val response = fieldsMapper(fieldsUseCase())
            _fieldsItemUI.value = response
        }
    }
}