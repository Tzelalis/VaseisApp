package com.example.vaseisapp.domain.topics

interface TopicsDataSource {
    suspend fun getExamTypeTopics(id : String) : List<TopicLesson>
}