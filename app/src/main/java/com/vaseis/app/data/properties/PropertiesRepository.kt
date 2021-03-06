package com.vaseis.app.data.properties

import com.vaseis.app.data.properties.model.RemoteFieldResponse
import com.vaseis.app.data.properties.model.RemotePropertiesExamTypeResponse

interface PropertiesRepository {

    suspend fun getPropertiesExamTypes(type : String) : RemotePropertiesExamTypeResponse

    suspend fun getFields() : RemoteFieldResponse
}