package com.example.vaseisapp.framework.topics

import com.example.vaseisapp.data.topics.model.RemoteTopicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopicsApi {
    @GET("/androidapi/api/single_topic.php")
    suspend fun getExamTypeTopics(@Query("id") id : String) : Response<RemoteTopicResponse>
}


