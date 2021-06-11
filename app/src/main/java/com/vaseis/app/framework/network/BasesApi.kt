package com.vaseis.app.framework.network

import com.vaseis.app.data.bases.model.RemoteDepartmentBases
import com.vaseis.app.data.bases.model.RemoteResponseUniversity
import com.vaseis.app.data.bases.model.RemoteStatsDept
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BasesApi {
    @GET("/api/index.php/v1.0/bases/department/{code}")
    suspend fun getBases(@Path("code") code: String): Response<List<RemoteDepartmentBases>>

    @GET("/api/index.php/universities")
    suspend fun getUniversities(): Response<RemoteResponseUniversity>

    @GET("/api/index.php/v1.0/statistics/department/{code}")
    suspend fun getDeptStats(@Path("code") code: String, @Query("type") type : String) : Response<List<RemoteStatsDept>>
}