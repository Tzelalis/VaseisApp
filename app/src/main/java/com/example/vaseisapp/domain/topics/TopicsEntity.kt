package com.example.vaseisapp.domain.topics

data class TopicLesson(
    val lesson : String,
    val position : Int,
    val topics : List<Topic>
)

data class Topic(
    val year : String,
    val imgSrc : String,
    val pdfUrl : String,
)