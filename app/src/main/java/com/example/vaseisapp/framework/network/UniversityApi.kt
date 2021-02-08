package com.example.vaseisapp.framework.network

import com.example.vaseisapp.data.university.RemoteResponseUniversity
import com.example.vaseisapp.data.university.RemoteUniversity
import retrofit2.Response
import retrofit2.http.GET

interface UniversityApi {

    @GET("/api/index.php/universities")
    suspend fun getUniversities() : Response<RemoteResponseUniversity>
}