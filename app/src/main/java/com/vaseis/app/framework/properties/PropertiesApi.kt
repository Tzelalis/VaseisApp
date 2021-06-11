package com.vaseis.app.framework.properties

import com.vaseis.app.data.calculator.model.RemoteCalculatorLesson
import com.vaseis.app.data.properties.model.RemotePropertiesExamTypeResponse
import retrofit2.Response
import retrofit2.http.GET

interface PropertiesApi {

    @GET("/androidapi/api/examtypes.php")
    suspend fun getPropertiesExamTypes() : Response<RemotePropertiesExamTypeResponse>

    @GET("/androidapi/api/examtypes.php")
    suspend fun getAllLessons() : Response<RemoteCalculatorLesson>
}