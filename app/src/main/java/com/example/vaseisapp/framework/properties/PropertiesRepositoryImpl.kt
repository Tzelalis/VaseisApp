package com.example.vaseisapp.framework.properties

import android.content.SharedPreferences
import com.example.vaseisapp.data.properties.PropertiesRepository
import com.example.vaseisapp.data.properties.model.RemotePropertiesExamTypeResponse
import com.example.vaseisapp.utils.handleExternalApiFormat
import javax.inject.Inject

class PropertiesRepositoryImpl @Inject constructor(
    private val api: PropertiesApi,
    private val prefs: SharedPreferences
) : PropertiesRepository {
    override suspend fun getPropertiesExamTypes(): RemotePropertiesExamTypeResponse {
        return api.getPropertiesExamTypes().handleExternalApiFormat()


    }
}