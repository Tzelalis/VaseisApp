package com.vaseis.app.framework.topics

import com.vaseis.app.data.topics.TopicsRepository
import com.vaseis.app.data.topics.model.RemoteTopicResponse
import com.vaseis.app.utils.handleExternalApiFormat
import javax.inject.Inject

class TopicsRepositoryImpl @Inject constructor(private val api: TopicsApi) : TopicsRepository {
    override suspend fun getExamTypeTopics(id: String): RemoteTopicResponse {
        return api.getExamTypeTopics(id).handleExternalApiFormat()
    }
}


