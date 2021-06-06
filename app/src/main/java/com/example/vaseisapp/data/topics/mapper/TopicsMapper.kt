package com.example.vaseisapp.data.topics.mapper

import com.example.vaseisapp.data.topics.model.RemoteTopic
import com.example.vaseisapp.data.topics.model.RemoteTopicLesson
import com.example.vaseisapp.data.topics.model.RemoteTopicResponse
import com.example.vaseisapp.domain.properties.PropertiesExamType
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.utils.mapAsync
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
