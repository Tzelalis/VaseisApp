package com.vaseis.app.ui.personalization.fields

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vaseis.app.base.BaseViewModel
import com.vaseis.app.ui.dashboard.accountcenter.model.PrefProperty
import com.vaseis.app.ui.dashboard.departmentcenter.filters.models.FieldFilterItem
import com.vaseis.app.ui.personalization.fields.mapper.MapperFieldPersonalizationItem
import com.vaseis.app.usecase.prefs.GetExamTypeUseCase
import com.vaseis.app.usecase.prefs.GetPrefsFieldsUseCase
import com.vaseis.app.usecase.prefs.SetPrefsFieldsUseCase
import com.vaseis.app.usecase.properties.GetFieldsUseCase

class FieldsViewModel @ViewModelInject constructor(
    private val getFieldsUseCase: GetFieldsUseCase,
    private val mapperFieldPersonalizationItem: MapperFieldPersonalizationItem,
    private val getExamTypeUseCase: GetExamTypeUseCase,
    private val getPrefsFieldsUseCase: GetPrefsFieldsUseCase,
    private val setPrefsFieldsUseCase: SetPrefsFieldsUseCase
) : BaseViewModel(){

    private var _fieldsUI = MutableLiveData<List<FieldFilterItem>>()
    val fieldsUI : LiveData<List<FieldFilterItem>> = _fieldsUI

    private var _savedUI = MutableLiveData<Unit>()
    val savedUI : LiveData<Unit> = _savedUI

    fun loadData()  {
        launch(true)    {
            val fields = mapperFieldPersonalizationItem(getFieldsUseCase())
            val prefsFields = getPrefsFieldsUseCase()

            for(pref in prefsFields)
                for(field in fields)
                if(pref.id == field.field.key) {
                    field.isSelected = true
                    break
                }

            _fieldsUI.value = fields
        }
    }

    fun savedData(property : List<PrefProperty>) {
        launch(true)    {
            setPrefsFieldsUseCase(property)
            _savedUI.value = Unit
        }
    }
}