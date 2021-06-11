package com.vaseis.app.domain.topics

interface TopicsDataSource {
    suspend fun getExamTypeTopics(id : String) : List<TopicLesson>
}