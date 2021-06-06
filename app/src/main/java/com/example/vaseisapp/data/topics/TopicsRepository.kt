package com.example.vaseisapp.data.topics

import com.example.vaseisapp.data.topics.model.RemoteTopicResponse

interface TopicsRepository {

    suspend fun getExamTypeTopics(id : String) : RemoteTopicResponse
}
