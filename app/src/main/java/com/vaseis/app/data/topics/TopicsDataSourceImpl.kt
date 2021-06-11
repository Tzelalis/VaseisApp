package com.vaseis.app.data.topics

import com.vaseis.app.data.topics.mapper.TopicsMapper
import com.vaseis.app.domain.topics.TopicLesson
import com.vaseis.app.domain.topics.TopicsDataSource
import javax.inject.Inject

class TopicsDataSourceImpl @Inject constructor(private val repo : TopicsRepository,
private val topicsMapper : TopicsMapper) : TopicsDataSource{
    override suspend fun getExamTypeTopics(id: String): List<TopicLesson> {
        return topicsMapper(repo.getExamTypeTopics(id))
    }
}

