package com.example.vaseisapp.data.topics

import com.example.vaseisapp.data.topics.mapper.TopicsMapper
import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.domain.topics.TopicsDataSource
import javax.inject.Inject

class TopicsDataSourceImpl @Inject constructor(private val repo : TopicsRepository,
private val topicsMapper : TopicsMapper) : TopicsDataSource{
    override suspend fun getExamTypeTopics(id: String): List<TopicLesson> {
        return topicsMapper(repo.getExamTypeTopics(id))
    }
}

