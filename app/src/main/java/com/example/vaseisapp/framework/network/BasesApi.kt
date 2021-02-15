package com.example.vaseisapp.framework.network

import com.example.vaseisapp.data.university.RemoteDepartment
import com.example.vaseisapp.data.university.RemoteResponseBases
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BasesApi {
    @GET("")
    suspend fun fetchDepartment(

    ): RemoteResponseBases
}