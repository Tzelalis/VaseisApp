package com.vaseis.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SaveStateLiveDataDelegate<T>(private val savedStateHandle: SavedStateHandle, private val key: String) : ReadWriteProperty<Any?, LiveData<T?>> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): MutableLiveData<T> {
        return savedStateHandle.getLiveData(key)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: LiveData<T?>) {
        savedStateHandle.getLiveData(key, value.value)
    }
}