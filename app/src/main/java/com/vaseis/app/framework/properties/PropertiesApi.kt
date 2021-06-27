package com.vaseis.app.framework.properties

import com.vaseis.app.data.properties.model.RemoteFieldResponse
import com.vaseis.app.data.calculator.model.RemoteCalculatorLesson
import com.vaseis.app.data.properties.model.RemotePropertiesExamTypeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertiesApi {

    @GET("/androidapi/api/examtypes.php")
    suspend fun getPropertiesExamTypes(@Query("type") type: String): Response<RemotePropertiesExamTypeResponse>

    @GET("/androidapi/api/examtypes.php")
    suspend fun getAllLessons(): Response<RemoteCalculatorLesson>

    @GET("/androidapi/api/fields.php")
    suspend fun getFields(): Response<RemoteFieldResponse>
}