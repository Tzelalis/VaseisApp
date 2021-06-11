package com.vaseis.app.framework.network

import com.vaseis.app.data.university.RemoteDepartment
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DepartmentApi {

    @GET("/api/index.php/departments/?details=full")
    suspend fun fetchAllDepartments(): List<RemoteDepartment>

    @GET("bases/department/{dept_id}")
    suspend fun fetchDepartment(
        @Path("dept_id") code: String,
        @Query("type") type: String = "gel-ime-gen",
        @Query("details") details: String = "full"
    ): RemoteDepartment
}