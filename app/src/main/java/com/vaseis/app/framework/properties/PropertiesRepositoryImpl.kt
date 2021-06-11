package com.vaseis.app.framework.properties

import android.content.SharedPreferences
import com.vaseis.app.data.properties.PropertiesRepository
import com.vaseis.app.data.properties.model.RemotePropertiesExamTypeResponse
import com.vaseis.app.utils.handleExternalApiFormat
import javax.inject.Inject

class PropertiesRepositoryImpl @Inject constructor(
    private val api: PropertiesApi,
    private val prefs: SharedPreferences
) : PropertiesRepository {
    override suspend fun getPropertiesExamTypes(): RemotePropertiesExamTypeResponse {
        return api.getPropertiesExamTypes().handleExternalApiFormat()


    }
}