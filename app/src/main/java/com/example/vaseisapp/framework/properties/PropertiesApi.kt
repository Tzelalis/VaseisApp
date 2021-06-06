package com.example.vaseisapp.framework.properties

import com.example.vaseisapp.data.calculator.model.RemoteCalculatorLesson
import com.example.vaseisapp.data.properties.model.RemotePropertiesExamType
import com.example.vaseisapp.data.properties.model.RemotePropertiesExamTypeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertiesApi {

    @GET("/androidapi/api/examtypes.php")
    suspend fun getPropertiesExamTypes() : Response<RemotePropertiesExamTypeResponse>

    @GET("/androidapi/api/examtypes.php")
    suspend fun getAllLessons() : Response<RemoteCalculatorLesson>
}