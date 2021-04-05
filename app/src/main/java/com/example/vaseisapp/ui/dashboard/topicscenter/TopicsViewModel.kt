package com.example.vaseisapp.ui.dashboard.topicscenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaseisapp.base.BaseViewModel
import com.example.vaseisapp.domain.topics.Topic
import com.example.vaseisapp.domain.topics.TopicLesson
import com.example.vaseisapp.ui.dashboard.topicscenter.model.ExamTypeItem
import com.example.vaseisapp.ui.dashboard.topicscenter.model.map
import com.example.vaseisapp.usecase.calculator.GetAllExamsTypesUseCase


class TopicsViewModel @ViewModelInject constructor(private val getAllExamsTypesUseCase: GetAllExamsTypesUseCase) : BaseViewModel() {
    private var _topicLessons = MutableLiveData<List<TopicLesson>>()
    val topicLesson: LiveData<List<TopicLesson>> = _topicLessons

    private var _examTypeUI = MutableLiveData<List<ExamTypeItem>>()
    val examTypeUI: LiveData<List<ExamTypeItem>> = _examTypeUI

    fun loadTopicLesons() {
        val dummy = TopicLesson(
            "Μαθηματικά", 1, listOf(
                Topic(
                    "2020",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2019",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2018",
                    "https://p.calameoassets.com/18051511wserasdf1509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2017",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2016",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2015",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2014",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2013",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                )
            )
        )
        val dummy1 = TopicLesson(
            "ΑΟΘ", 1, listOf(
                Topic(
                    "2020",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2019",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2018",
                    "https://p.calameoassets.com/18051511wserasdf1509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2017",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2016",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2015",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2014",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2013",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                )
            )
        )
        val dummy2 = TopicLesson(
            "ΑΕΠΠ", 1, listOf(
                Topic(
                    "2020",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2019",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2018",
                    "https://p.calameoassets.com/18051511wserasdf1509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2017",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2016",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2015",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2014",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2013",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                )
            )
        )
        val dummy3 = TopicLesson(
            "Νεοελληνική Γλώσσα", 1, listOf(
                Topic(
                    "2020",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2019",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2018",
                    "https://p.calameoassets.com/18051511wserasdf1509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2017",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2016",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2015",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2014",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2013",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                )
            )
        )
        val dummy4 = TopicLesson(
            "Αγγλικά", 1, listOf(
                Topic(
                    "2020",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2019",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2018",
                    "https://p.calameoassets.com/18051511wserasdf1509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2017",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2016",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2015",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2014",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                ),
                Topic(
                    "2013",
                    "https://p.calameoassets.com/180515111509-087734d3ab9181b3dbabd2c3eab490b6/p1.jpg",
                    "https://eudoxus.gr/files/FEK_1068B_19.03.2021.pdf"
                )
            )
        )

        _topicLessons.value = listOf(dummy, dummy1, dummy2, dummy3, dummy4)
    }

    fun loadGroups() {
        launch(true) {
            _examTypeUI.value = map(getAllExamsTypesUseCase())
        }

    }
}