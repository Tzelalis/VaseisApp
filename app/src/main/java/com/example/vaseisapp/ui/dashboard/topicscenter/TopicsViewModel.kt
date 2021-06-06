package com.example.vaseisapp.ui.dashboard.topicscenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.ui.dashboard.topicscenter.model.map
import com.example.vaseisapp.usecase.calculator.GetPropertiesExamsTypes
import com.example.vaseisapp.usecase.prefs.GetExamTypeUseCase
import com.example.vaseisapp.usecase.topics.GetTopicsByExamType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TopicsViewModel @ViewModelInject constructor(
    private val getAllExamsTypesUseCase: GetPropertiesExamsTypes,
    private val getTopicsByExamTypeUseCase: GetTopicsByExamType,
    private val getExamTypeUseCase: GetExamTypeUseCase
) : BaseViewModel() {


    private var _examTypeUI = MutableLiveData<List<ExamTypeItem>>()
    val examTypeUI: LiveData<List<ExamTypeItem>> = _examTypeUI

    private var _topicLessons = MutableLiveData<List<TopicLesson>>()
    val topicLesson: LiveData<List<TopicLesson>> = _topicLessons

    fun loadTopicsByExamType(examTypeId: String)  {
        launch(true){
            val result = getTopicsByExamTypeUseCase(examTypeId)
            _topicLessons.value = result
        }
    }

    fun loadExamTypes() {
        launch(true) {
            val result = map(getAllExamsTypesUseCase())
            (result.firstOrNull { it.examType.id == getExamTypeUseCase().id } ?: result[0]).isSelected = true
            _examTypeUI.value = result
        }
    }
}