package com.vaseis.app.data.topics

import com.vaseis.app.data.topics.model.RemoteTopicResponse

interface TopicsRepository {

    suspend fun getExamTypeTopics(id : String) : RemoteTopicResponse
}
