package com.vaseis.app.usecase.topics

import com.vaseis.app.domain.topics.TopicLesson
import com.vaseis.app.domain.topics.TopicsDataSource
import javax.inject.Inject

class GetTopicsByExamType @Inject constructor(private val dataSource: TopicsDataSource) {
    suspend operator fun invoke(id: String): List<TopicLesson> {
        return dataSource.getExamTypeTopics(id)
    }
}


