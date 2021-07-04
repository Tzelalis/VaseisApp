package com.vaseis.app.framework.calculator

import com.vaseis.app.data.calculator.model.RemoteCalculatorResponse
import retrofit2.Response
import retrofit2.http.GET


interface CalculatorApi {

    @GET("/androidapi/api/calculatorlessons.php")
    suspend fun getAllCalculator() : Response<RemoteCalculatorResponse>

}