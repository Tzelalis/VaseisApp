package com.vaseis.app.data.topics.mapper

import com.vaseis.app.data.topics.model.RemoteTopic
import com.vaseis.app.data.topics.model.RemoteTopicLesson
import com.vaseis.app.data.topics.model.RemoteTopicResponse
import com.vaseis.app.domain.topics.Topic
import com.vaseis.app.domain.topics.TopicLesson
import com.vaseis.app.utils.mapAsync
import javax.inject.Inject

class TopicsMapper @Inject constructor() {

    suspend operator fun invoke(response: RemoteTopicResponse): List<TopicLesson> {
        return mapTopicLessons(response.data?.toMutableList())
    }

    private suspend fun mapTopicLessons(mappable: MutableList<RemoteTopicLesson>?): List<TopicLesson> {
        return mappable?.map { it.toTopicLesson() } ?: emptyList()
    }

    private suspend fun RemoteTopicLesson.toTopicLesson() : TopicLesson {
        return TopicLesson(
            lessonId ?:"",
            fullName ?:"",
            shortName ?:"",
            mapTopics(topics?.toMutableList()).sortedBy { it.year }.reversed()
        )
    }

    private suspend fun mapTopics(mappable: MutableList<RemoteTopic>?): List<Topic> {
        return mappable?.mapAsync { it.toTopic() } ?: emptyList()
    }

    private fun RemoteTopic.toTopic() : Topic {
        return Topic(
            year ?:"",
            imgUrl ?:"",
            pdfUrl ?:""
        )
    }
}
