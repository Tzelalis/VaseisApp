package com.example.vaseisapp.usecase.topics

import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.domain.topics.TopicsDataSource
import javax.inject.Inject

class GetTopicsByExamType @Inject constructor(private val dataSource: TopicsDataSource) {
    suspend operator fun invoke(id: String): List<TopicLesson> {
        return dataSource.getExamTypeTopics(id)
    }
}


