package com.vaseis.app.framework.topics

import com.vaseis.app.data.topics.model.RemoteTopicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopicsApi {
    @GET("/androidapi/api/single_topic.php")
    suspend fun getExamTypeTopics(@Query("id") id : String) : Response<RemoteTopicResponse>
}


