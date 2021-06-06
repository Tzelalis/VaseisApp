package com.example.vaseisapp.framework.network

import com.example.vaseisapp.data.bases.model.RemoteDepartmentBases
import com.example.vaseisapp.data.bases.model.RemoteResponseUniversity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BasesApi {
    @GET("/api/index.php/v1.0/bases/department/{code}")
    suspend fun getBases(@Path("code") code: String): Response<List<RemoteDepartmentBases>>

    @GET("/api/index.php/universities")
    suspend fun getUniversities(): Response<RemoteResponseUniversity>
}