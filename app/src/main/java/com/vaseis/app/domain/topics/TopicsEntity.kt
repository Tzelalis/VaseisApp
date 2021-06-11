package com.vaseis.app.domain.topics

data class TopicLesson(
    val lessonId : String,
    val fullName : String,
    val shortName : String,
    val topics : List<Topic>
)

data class Topic(
    val year : String,
    val imgSrc : String,
    val pdfUrl : String
)