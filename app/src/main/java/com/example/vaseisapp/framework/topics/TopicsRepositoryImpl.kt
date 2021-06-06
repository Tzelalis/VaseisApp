package com.example.vaseisapp.framework.topics

import com.example.vaseisapp.data.topics.TopicsRepository
import com.example.vaseisapp.data.topics.model.RemoteTopicResponse
import com.example.vaseisapp.utils.handleExternalApiFormat
import javax.inject.Inject

class TopicsRepositoryImpl @Inject constructor(private val api: TopicsApi) : TopicsRepository {
    override suspend fun getExamTypeTopics(id: String): RemoteTopicResponse {
        return api.getExamTypeTopics(id).handleExternalApiFormat()
    }
}


