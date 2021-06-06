package com.example.vaseisapp.data.properties

import com.example.vaseisapp.data.properties.model.RemotePropertiesExamType
import com.example.vaseisapp.data.properties.model.RemotePropertiesExamTypeResponse
import com.example.vaseisapp.domain.properties.PropertiesExamType

interface PropertiesRepository {

    suspend fun getPropertiesExamTypes() : RemotePropertiesExamTypeResponse
}